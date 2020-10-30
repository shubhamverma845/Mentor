package com.sirion.userMicroservice.Controller;


import com.sirion.userMicroservice.Dto.MentorPOJO;
import com.sirion.userMicroservice.Dto.UserDto;
import com.sirion.userMicroservice.Model.User;
import com.sirion.userMicroservice.Service.UserService;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;


class UserPOJO{
    String username;
    String firstname;
    String lastname;
    String lORm;

    public UserPOJO(String username, String firstname, String lastname, String lORm) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.lORm = lORm;
    }

    public UserPOJO(User user) {

        this.username = user.getUsername();
        this.firstname = user.getFirstName();
        this.lastname = user.getLastName();
        this.lORm = user.getlORm();

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getlORm() {
        return lORm;
    }

    public void setlORm(String lORm) {
        this.lORm = lORm;
    }
}

@RestController
@RequestMapping(value = "/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    //createUser
    @PostMapping(value = "/createUser", headers = "Accept=application/json")
    public ResponseEntity<String> createUser(@RequestBody User user){

        userService.createUser(user);
        return new ResponseEntity<>("User Created", HttpStatus.OK);
    }

    //signUp
    @PostMapping(value = "/signUp", headers = "Accept=application/json")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto){

        User user1 = userService.getByUsername(userDto.getUsername());

        if (user1 != null){
            logger.error("User with username::" + userDto.getUsername() + " already exists!!!");
            return new ResponseEntity<>("Username already exists!!", HttpStatus.CONFLICT);
        }

        User user = userService.signUpUser(userDto);

        if(user.getlORm().equals("m")){

            MentorPOJO mentorPOJO = new MentorPOJO(user.getId(), user.getUsername());
            createMentor(mentorPOJO);

        }

        return new ResponseEntity<>("User Created", HttpStatus.OK);
    }

    @PostMapping(value = "/createMultipleUsers", headers = "Accept=application/json")
    public void createMultipleUsers(@RequestBody List<User> users){
        for (User user:users){
            userService.createUser(user);
        }
    }

    //getUser
    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id){
        User user = userService.getUserById(id);

        if (user == null){
            logger.error("User Does not exists!!");
            return new ResponseEntity<>(NOT_FOUND);
        }

        return new ResponseEntity<>(user, OK);
    }

    @GetMapping(value = "/getAllUsers", headers = "Accept=application/json")
    public List<UserPOJO> getAllUsers(){

        List<User> users = userService.getAllUser();

        List<UserPOJO> userPOJOS = new ArrayList<>();

        for (User user:
             users) {
            userPOJOS.add(new UserPOJO(user));
        }

        return userPOJOS;
    }

    @DeleteMapping(value = "/deleteUser/{id}", headers = "Accept=application/json")
    public void deleteUserById(@PathVariable("id") long id){
        userService.deleteUserById(id);
    }

    //method to create Mentor
    public void createMentor(MentorPOJO mentorPOJO){

        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:8962/mentor/createMentor";
        URI uri =  null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MentorPOJO> entity = new HttpEntity<>(mentorPOJO,headers);

        assert uri != null;
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
    }

}
