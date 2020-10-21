package com.sirion.userMicroservice.Controller;


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

class MentorPOJO{
    long id;
    String username;

    public MentorPOJO() {
    }

    public MentorPOJO(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    //createUser
    @PostMapping(value = "/createUser", headers = "Accept=application/json")
    public void createUser(@RequestBody User user){
        userService.createUser(user);

        if(user.getlORm().equals("m")){

            RestTemplate restTemplate = new RestTemplate();

            final String baseUrl = "http://localhost:8962/mentor/createMentor";
            URI uri =  null;
            try {
                uri = new URI(baseUrl);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            MentorPOJO m = new MentorPOJO();
            m.setId(user.getId());
            m.setUsername(user.getFirstName() + "_" + user.getLastName());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<MentorPOJO> entity = new HttpEntity<>(m,headers);

            assert uri != null;
            ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

//            System.out.println(result.getBody());
        }
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

    @GetMapping(value = "getAllUsers", headers = "Accept=application/json")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @DeleteMapping(value = "/deleteUser/{id}", headers = "Accept=application/json")
    public void deleteUserById(@PathVariable("id") long id){
        userService.deleteUserById(id);
    }
}
