package com.sirion.searchMicroservice.Controller;


import com.sirion.searchMicroservice.Model.Mentor;
import com.sirion.searchMicroservice.Model.MentorSkill;
import com.sirion.searchMicroservice.Service.MentorService;
import com.sirion.searchMicroservice.Service.MentorSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentorSkill")
public class MentorSkillController {

    @Autowired
    MentorSkillService mentorSkillService;

    @Autowired
    MentorService mentorService;

    @GetMapping()
    public List<MentorSkill> getAllSkills(){
        return mentorSkillService.getAllMentorSkill();
    }

    @PostMapping(value = "/createMentorSkill", headers = "Accept=application/json")
    public ResponseEntity<Void> createMentorSkill(@RequestBody MentorSkill mentorSkill) {

        Mentor mentor = mentorService.findById(mentorSkill.getMentorId());

        if(mentor == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        mentorSkillService.createMentorSkill(mentorSkill);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}