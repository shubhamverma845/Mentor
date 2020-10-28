package com.sirion.userMicroservice.Service;

import com.sirion.userMicroservice.Model.MyUserDetails;
import com.sirion.userMicroservice.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);

        System.out.println(user.getFirstName());


        if(user == null){
//            System.out.println("-------------------notfound");
            throw new UsernameNotFoundException("Not Found::" + username);

        }

        return new MyUserDetails(user);
    }
}
