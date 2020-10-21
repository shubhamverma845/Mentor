package com.sirion.userMicroservice.Service;


import com.sirion.userMicroservice.Model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    void deleteUserById(long id);
    List<User> getAllUser();
    User getUserById(long id);
}
