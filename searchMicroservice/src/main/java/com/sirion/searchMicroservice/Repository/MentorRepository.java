package com.sirion.searchMicroservice.Repository;


import com.sirion.searchMicroservice.Model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorRepository extends JpaRepository<Mentor, Long> {

}
