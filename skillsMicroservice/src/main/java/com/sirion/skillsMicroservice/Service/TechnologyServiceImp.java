package com.sirion.skillsMicroservice.Service;


import com.sirion.skillsMicroservice.Model.Technology;
import com.sirion.skillsMicroservice.Repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyServiceImp implements TechnologyService {

    @Autowired
    TechnologyRepository technologyRepository;

    @Override
    public void createTechnology(Technology technology) {
        technologyRepository.save(technology);
    }

    @Override
    public Technology findById(long id) {
        return technologyRepository.findById(id).get();
    }

    @Override
    public List<Technology> getAllTechnology() {
        return technologyRepository.findAll();
    }

    @Override
    public void deleteTechnologyById(long id) {
        technologyRepository.deleteById(id);
    }

    @Override
    public List<Technology> findByName(String name){
//        System.out.println("fffff");
        return technologyRepository.findByTechName(name);
    }

    @Override
    public Technology updateTechnology(Technology technology) {
        return technologyRepository.save(technology);
    }

    @Override
    public List<Technology> getTechnologies(Integer pageNo, Integer pageSize, String sortBy, String ord) {

        Pageable paging;

        if (ord.equals("asc")){
            paging = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC,sortBy));
        } else {
            paging = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC,sortBy));
        }

        Page<Technology> pagedResult = technologyRepository.findAll((org.springframework.data.domain.Pageable) paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
