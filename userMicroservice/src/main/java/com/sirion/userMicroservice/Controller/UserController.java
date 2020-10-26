package com.sirion.userMicroservice.Controller;


import com.sirion.userMicroservice.Dto.MentorPOJO;
import com.sirion.userMicroservice.Dto.UserDto;
import com.sirion.userMicroservice.Model.User;
import com.sirion.userMicroservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    //createUser or signUp
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
            return new ResponseEntity<>("Username already exists!!", HttpStatus.CONFLICT);
        }

        User user = userService.signUpUser(userDto);

        if(user.getlORm().equals("m")){

            MentorPOJO mentorPOJO = new MentorPOJO(user.getId(), user.getUsername());
            createMentor(mentorPOJO);
//            RestTemplate restTemplate = new RestTemplate();
//
//            final String baseUrl = "http://localhost:8962/mentor/createMentor";
//            URI uri =  null;
//            try {
//                uri = new URI(baseUrl);
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            }
//
//            MentorPOJO m = new MentorPOJO(user.getId(), user.getUsername());
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<MentorPOJO> entity = new HttpEntity<>(m,headers);
//
//            assert uri != null;
//            ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
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
            return new ResponseEntity<>(NOT_FOUND);
        }

        return new ResponseEntity<>(user, OK);
    }

    @GetMapping(value = "/getAllUsers", headers = "Accept=application/json")
    public List<User> getAllUsers(){
        return userService.getAllUser();
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
