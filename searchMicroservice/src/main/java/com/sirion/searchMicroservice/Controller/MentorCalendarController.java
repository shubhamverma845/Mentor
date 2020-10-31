package com.sirion.searchMicroservice.Controller;


import com.sirion.searchMicroservice.Model.MentorCalendar;
import com.sirion.searchMicroservice.Service.MentorCalendarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/mentorCalendar")
public class MentorCalendarController {

    Logger logger = LoggerFactory.getLogger(MentorCalendarController.class);

    @Autowired
    MentorCalendarService mentorCalendarService;


    //get all mentor calendar
    @GetMapping(value = "/getAllMentorCalendar", headers = "Accept=application/json")
    public List<MentorCalendar> getAllMentorCalendar(){
        return mentorCalendarService.getAllMentorCalendar();
    }


    //get mentor calendar by id
    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<MentorCalendar> getMentorCalendarById(@PathVariable("id") long id){

        try {
            return new ResponseEntity<>(mentorCalendarService.findById(id), HttpStatus.OK);
        } catch (Exception e){
            logger.warn("Invalid ID");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //create mentor calendar
    @PostMapping(value = "/createMentorCalendar", headers = "Accept=application/json")
    public ResponseEntity<String> createMentorCalendar(@RequestBody MentorCalendar mentorCalendar){

        System.out.println(mentorCalendar.getStartTime());

        mentorCalendarService.createMentorCalendar(mentorCalendar);
        return new ResponseEntity<>("Mentor Calendar Created with ID:" + mentorCalendar.getId(), HttpStatus.CREATED);
    }


    //create multiple mentor calendar
    @PostMapping(value = "/createMultipleMentorCalendar", headers = "Accept=application/json")
    public ResponseEntity<String> createMultipleMentorCalendar(@RequestBody List<MentorCalendar> mentorCalendars){

        for (MentorCalendar mentorCalendar: mentorCalendars){
            mentorCalendarService.createMentorCalendar(mentorCalendar);
        }

        return new ResponseEntity<>("Mentor Calendars Created", HttpStatus.CREATED);
    }


    //delete mentor calendar by Id
    @DeleteMapping(value = "/deleteMentorCalendar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> deleteMentorCalendar(@PathVariable("id") long id){
        mentorCalendarService.deleteMentorCalendar(id);
        return new ResponseEntity<>("Mentor Calendar deleted with ID:" + id, HttpStatus.NO_CONTENT);
    }


    //update mentor calendar
    @PutMapping(value = "/updateMentorCalendar", headers = "Accept=application/json")
    public ResponseEntity<String> updateMentorCalendar(@RequestBody MentorCalendar currentMentorCalendar){
        mentorCalendarService.updateMentorCalendar(currentMentorCalendar);
        return new ResponseEntity<>("Mentor Calendar details updated!!", HttpStatus.OK);
    }

}
