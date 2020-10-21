package com.sirion.skillsMicroservice.Repository;


import com.sirion.skillsMicroservice.Model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    List<Technology> findByTechName(String name);
}
