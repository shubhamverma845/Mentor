package com.sirion.skillsMicroservice.Service;


import com.sirion.skillsMicroservice.Model.Technology;

import java.util.List;

public interface TechnologyService {
    void createTechnology(Technology technology);
    Technology findById(long id);
    List<Technology> getAllTechnology();
    void deleteTechnologyById(long id);
    Technology updateTechnology(Technology technology);
    List<Technology> getTechnologies(String sortBy, Integer pageSize, Integer pageNo);
    List<Technology> findByName(String name);
}
