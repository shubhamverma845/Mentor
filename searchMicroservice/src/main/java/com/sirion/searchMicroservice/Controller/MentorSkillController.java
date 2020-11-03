package com.sirion.searchMicroservice.Controller;


import com.sirion.searchMicroservice.Model.MentorSkill;
import com.sirion.searchMicroservice.Service.MentorService;
import com.sirion.searchMicroservice.Service.MentorSkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mentorSkill")
public class MentorSkillController {

    Logger logger = LoggerFactory.getLogger(MentorSkillController.class);

    @Autowired
    MentorSkillService mentorSkillService;

    @Autowired
    MentorService mentorService;


    //get all mentors skills
    @GetMapping()
    public List<MentorSkill> getAllSkills(){
        return mentorSkillService.getAllMentorSkill();
    }


    //create mentor's skill
    @PostMapping(value = "/createMentorSkill", headers = "Accept=application/json")
    public ResponseEntity<String> createMentorSkill(@RequestBody MentorSkill mentorSkill) {

        try {
            mentorService.findById(mentorSkill.getMentorId());
            doesSkillExists(mentorSkill.getSkillId());
            mentorSkillService.createMentorSkill(mentorSkill);
            return new ResponseEntity<>("Mentor Skill created with ID:" + mentorSkill.getId(),
                    HttpStatus.CREATED);

        } catch (Exception e){
            logger.warn("Invalid Mentor ID and Skill ID");
            return new ResponseEntity<>("Invalid IDs", HttpStatus.NOT_FOUND);
        }
    }


    //create multiple mentor's skills
    @PostMapping(value = "/createMultipleMentorSkill", headers = "Accept=application/json")
    public ResponseEntity<String> createMultipleMentorSkill(@RequestBody List<MentorSkill> mentorSkills){

        for (MentorSkill mentorSkill: mentorSkills){
            mentorSkillService.createMentorSkill(mentorSkill);
        }

        return new ResponseEntity<>("Mentor Skills Created", HttpStatus.CREATED);
    }


    //to check valid skillId
    public void doesSkillExists(long skillId) throws NoSuchElementException{
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8961/technology/" + Long.toString(skillId);
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl).build().toUri();
        ResponseEntity<Object> result = restTemplate.getForEntity(uri,Object.class);
    }
}