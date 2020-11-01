package com.sirion.userMicroservice.Controller;


import com.sirion.userMicroservice.Dto.MentorPOJO;
import com.sirion.userMicroservice.Dto.UserDto;
import com.sirion.userMicroservice.Model.AuthenticationRequest;
import com.sirion.userMicroservice.Model.AuthenticationResponse;
import com.sirion.userMicroservice.Model.User;
import com.sirion.userMicroservice.Service.MyUserDetailsService;
import com.sirion.userMicroservice.Service.UserService;
import com.sirion.userMicroservice.Util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtUtil jwtTokenUtil;


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
            logger.error("Username already exists!!!");
            return new ResponseEntity<>("Username already exists!!", HttpStatus.CONFLICT);
        }

        User user = userService.signUpUser(userDto);

        if(user.getlORm().equals("m")){

            MentorPOJO mentorPOJO = new MentorPOJO(user.getUsername());
            createMentor(mentorPOJO);
        }

        return new ResponseEntity<>(String.format("User Created with ID:%d and Username:%s", user.getId(),user.getUsername()),
                HttpStatus.OK);
    }


    //create multiple users
    @PostMapping(value = "/createMultipleUsers", headers = "Accept=application/json")
    public ResponseEntity<String> createMultipleUsers(@RequestBody List<User> users){
        for (User user:users){
            userService.createUser(user);
        }
        return new ResponseEntity<>("Multiple Users created!!", HttpStatus.CREATED);
    }


    //get user by userId
    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id){

        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        } catch (Exception e){
            logger.warn("User Does not exists!!");
            return new ResponseEntity<>(NOT_FOUND);
        }
    }


    //get all users
    @GetMapping(value = "/getAllUsers", headers = "Accept=application/json")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }


    //delete user by Id
    @DeleteMapping(value = "/deleteUser/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>("User deleted with ID:" + id, HttpStatus.NO_CONTENT);
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