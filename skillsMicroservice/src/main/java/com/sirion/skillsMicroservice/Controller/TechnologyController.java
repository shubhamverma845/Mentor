package com.sirion.skillsMicroservice.Controller;


import com.sirion.skillsMicroservice.Model.Technology;
import com.sirion.skillsMicroservice.Service.TechnologyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/technology")
public class TechnologyController {

    Logger logger = LoggerFactory.getLogger(TechnologyController.class);

    @Autowired
    TechnologyService technologyService;


    //addSkill
    @PostMapping(value = "/createTechnology", headers = "Accept=application/json")
    public ResponseEntity<String> createTechnology(@RequestBody Technology technology){

        technologyService.createTechnology(technology);
        return new ResponseEntity<>("Technology created with Id:" + technology.getId(), HttpStatus.CREATED);
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
    public ResponseEntity<String> createMultipleTechnologies(@RequestBody List<Technology> technologies){

        for(Technology technology:technologies){
            technologyService.createTechnology(technology);
        }

        return new ResponseEntity<>("Multiple Technologies Created!!",HttpStatus.CREATED);
    }


    //getSkill
    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<Technology> getTechnologyById(@PathVariable("id") long id){

        try {
            return new ResponseEntity<>(technologyService.findById(id), HttpStatus.OK);
        } catch (Exception e){
            logger.warn("Invalid technology ID");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //get all skills
    @GetMapping(value = "/getAllTechnologies", headers = "Accept=application/json")
    public List<Technology> getAllTechnology(){
        return technologyService.getAllTechnology();
    }


    //delete skill
    @DeleteMapping(value = "/deleteTechnology/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> deleteTechnology(@PathVariable("id") long id){

        try {
            technologyService.deleteTechnologyById(id);
            return new ResponseEntity<>("Technology deleted with ID:" + id, HttpStatus.NO_CONTENT);
        } catch (Exception e){
            logger.warn("Invalid technology ID");
            return new ResponseEntity<>("Invalid technology ID", HttpStatus.NOT_FOUND);
        }
    }


    //update skill
    @PutMapping(value = "/updateTechnology", headers = "Accept=application/json")
    public ResponseEntity<String> updateTechnology(@RequestBody Technology currentTechnology){

        try {
            Technology technology = technologyService.findById(currentTechnology.getId());
            technologyService.updateTechnology(currentTechnology);
            return new ResponseEntity<>("Technology updated!!", HttpStatus.OK);
        } catch (Exception e){
            logger.warn("Invalid technology ID");
            return new ResponseEntity<>("Invalid technology ID", HttpStatus.NOT_FOUND);
        }
    }


    //get skill name
    @GetMapping(value = "/getSkillName", headers = "Accept=application/json")
    public ResponseEntity<String> getSkillName(@RequestParam long skillId){

        try {
            return new ResponseEntity<>(technologyService.findById(skillId).getTechName(), HttpStatus.OK);
        } catch (Exception e){
            logger.warn("Invalid Technology Id");
            return new ResponseEntity<>("Invalid Technology Id", HttpStatus.NOT_FOUND);
        }
    }


    //pagination
    @GetMapping
    public ResponseEntity<List<Technology>> getTechnologies(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String ord)
    {
        List<Technology> list = technologyService.getTechnologies(pageNo, pageSize, sortBy, ord);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }


    //get skill ids by skill name
    @GetMapping(value = "/getTechnologyId")
    public Long[] getTechnologyId(@RequestParam String name){

        List<Technology> technologies;

        if (name.isEmpty()){
            technologies = technologyService.getAllTechnology();
        } else{
            technologies = technologyService.findByName(name);
        }

        Long[] list = new Long[technologies.size()];

        int i = 0;
        for (Technology technology: technologies){
            list[i++] = technology.getId();
        }
        return list;
    }
}