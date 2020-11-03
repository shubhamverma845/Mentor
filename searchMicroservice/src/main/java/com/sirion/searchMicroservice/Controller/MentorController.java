package com.sirion.searchMicroservice.Controller;


import com.sirion.searchMicroservice.Model.Mentor;
import com.sirion.searchMicroservice.Service.MentorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mentor")
public class MentorController {

    Logger logger = LoggerFactory.getLogger(MentorController.class);

    @Autowired
    MentorService mentorService;


    //get all mentors
    @GetMapping(value = "/getAllMentors", headers = "Accept=application/json")
    public List<Mentor> getAllMentor(){
        return mentorService.getAllMentor();
    }


    //get mentor by mentorId
    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<Mentor> getMentorById(@PathVariable("id") long id){

        try {
            return new ResponseEntity<Mentor>(mentorService.findById(id), HttpStatus.OK);
        } catch (Exception e){
            logger.warn("Invalid Mentor Id");
            return new ResponseEntity<Mentor>(HttpStatus.NOT_FOUND);
        }
    }


    //create mentor
    @PostMapping(value = "/createMentor", headers = "Accept=application/json")
    public ResponseEntity<String> createMentor(@RequestBody Mentor mentor){
        mentorService.createMentor(mentor);
        return new ResponseEntity<>("Mentor Created with Mentor ID:" + mentor.getId(), HttpStatus.CREATED);
    }


    //create multiple mentors
    @PostMapping(value = "/createMultipleMentors", headers = "Accept=application/json")
    public ResponseEntity<String> createMultipleMentors(@RequestBody List<Mentor> mentors){

        for (Mentor mentor: mentors){
            mentorService.createMentor(mentor);
        }

        return new ResponseEntity<>("Mentors Created", HttpStatus.CREATED);
    }


    //delete mentor by mentorId
    @DeleteMapping(value = "/deleteMentor/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> deleteMentor(@PathVariable("id") long id){

        try {
            mentorService.deleteMentorById(id);
            return new ResponseEntity<>("Mentor deleted with ID:" + id, HttpStatus.NO_CONTENT);
        } catch (Exception e){
            logger.warn("Invalid Mentor ID!!");
            return new ResponseEntity<>("Invalid Mentor ID!!", HttpStatus.NOT_FOUND);
        }
    }


    //update mentor
    @PutMapping(value = "/updateMentor", headers = "Accept=application/json")
    public ResponseEntity<String> updateMentor(@RequestBody Mentor currentMentor){
        mentorService.updateMentor(currentMentor);
        return new ResponseEntity<>("Mentor details updated!!", HttpStatus.OK);
    }
}
