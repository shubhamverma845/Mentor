package com.sirion.skillsMicroservice.Controller;


import com.sirion.skillsMicroservice.Model.Technology;
import com.sirion.skillsMicroservice.Service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/technology")
public class TechnologyController {

    @Autowired
    TechnologyService technologyService;

    //addSkill
    @PostMapping(value = "/createTechnology", headers = "Accept=application/json")
    public ResponseEntity<Void> createTechnology(@RequestBody Technology technology){
        System.out.println("Creating Technology::" + technology.getTechName());
        technologyService.createTechnology(technology);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //searchSkills
    @GetMapping(value = "/searchByName")
    public List<Technology> getTechnologyByName(@RequestParam String name){
        if(name.isEmpty()){
            return technologyService.getAllTechnology();
        }
        return technologyService.findByName(name);
    }

    //addSkills
    @PostMapping(value = "/createMultipleTechnologies", headers = "Accept=application/json")
    public ResponseEntity<Void> createMultipleTechnologies(@RequestBody List<Technology> technologies){
        System.out.println("Creating multiple technologies.....");

        for(Technology technology:technologies){
            technologyService.createTechnology(technology);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //getSkill
    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<Technology> getTechnologyById(@PathVariable("id") long id){
        Technology technology = technologyService.findById(id);
        if (technology == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(technology, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllTechnologies", headers = "Accept=application/json")
    public List<Technology> getAllTechnology(){
        return technologyService.getAllTechnology();
    }

    @DeleteMapping(value = "/deleteTechnology/{id}", headers = "Accept=application/json")
    public ResponseEntity<Technology> deleteTechnology(@PathVariable("id") long id){
        Technology technology = technologyService.findById(id);
        if (technology == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        technologyService.deleteTechnologyById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/updateTechnology", headers = "Accept=application/json")
    public ResponseEntity<String> updateTechnology(@RequestBody Technology currentTechnology){
        Technology technology = technologyService.findById(currentTechnology.getId());
        if (technology == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        technologyService.updateTechnology(currentTechnology);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping(value = "/getSkillName", headers = "Accept=application/json")
    public String getSkillName(@RequestParam long skillId){
        return technologyService.findById(skillId) == null ? "N-A" : technologyService.findById(skillId).getTechName();
    }

    @GetMapping
    public ResponseEntity<List<Technology>> getTechnologies(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String ord)
    {
        List<Technology> list = technologyService.getTechnologies(pageNo, pageSize, sortBy, ord);

//        System.out.println("Page::" + pageNo);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/getTechnologyId")
    public Long[] getTechnologyId(@RequestParam String name){

//        List<Long> list = new ArrayList<>();

        List<Technology> technologies;

//        System.out.println(name);

        if (name.isEmpty()){
            technologies = technologyService.getAllTechnology();
        }

        technologies = technologyService.findByName(name);

        Long[] list = new Long[technologies.size()];

//        System.out.println(technologies);

        int i = 0;
        for (Technology technology: technologies){
            list[i++] = technology.getId();
//            System.out.println(technology.getId());
        }

        return list;
    }

}