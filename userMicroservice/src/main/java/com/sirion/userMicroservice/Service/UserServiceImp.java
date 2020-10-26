package com.sirion.userMicroservice.Service;


import com.sirion.userMicroservice.Dto.UserDto;
import com.sirion.userMicroservice.Model.User;
import com.sirion.userMicroservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User signUpUser(UserDto userDto) {
        User user = new User(passwordEncoder.encode(userDto.getPassword()),
                userDto.getFirstName(),
                userDto.getUsername(),
                userDto.getLastName(),
                userDto.getlORm());

        return userRepository.save(user);

    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }


    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
