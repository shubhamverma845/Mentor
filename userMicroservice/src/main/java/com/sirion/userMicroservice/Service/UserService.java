package com.sirion.userMicroservice.Service;


import com.sirion.userMicroservice.Dto.UserDto;
import com.sirion.userMicroservice.Model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    void deleteUserById(long id);
    List<User> getAllUser();
    User getUserById(long id);
    User getByUsername(String username);
    User signUpUser(UserDto userDto);
}
