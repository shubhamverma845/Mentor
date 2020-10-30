package com.sirion.skillsMicroservice.Repository;


import com.sirion.skillsMicroservice.Model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM technologies_table WHERE ", nativeQuery = true)
    List<Technology> getTechnologies(int pageNo, int pageSize, String sortBy, String order);

    List<Technology> findByTechName(String name);
}
