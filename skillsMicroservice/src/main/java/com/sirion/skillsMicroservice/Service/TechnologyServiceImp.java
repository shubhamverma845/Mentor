package com.sirion.skillsMicroservice.Service;


import com.sirion.skillsMicroservice.Model.Technology;
import com.sirion.skillsMicroservice.Repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TechnologyServiceImp implements TechnologyService {

    @Autowired
    TechnologyRepository technologyRepository;

    @Override
    public void createTechnology(Technology technology) {
        technologyRepository.save(technology);
    }

    @Override
    public Technology findById(long id) throws NoSuchElementException{
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
        return technologyRepository.findByTechName(name);
    }

    @Override
    public Technology updateTechnology(Technology technology) {
        return technologyRepository.save(technology);
    }

    @Override
    public List<Technology> getTechnologies(String sortBy, Integer pageSize, Integer pageNo) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Technology> page = technologyRepository.findAll(pageable);

        if (page.hasContent()){
            return page.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
