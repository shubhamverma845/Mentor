package com.sirion.userMicroservice.Repository;


import com.sirion.userMicroservice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
