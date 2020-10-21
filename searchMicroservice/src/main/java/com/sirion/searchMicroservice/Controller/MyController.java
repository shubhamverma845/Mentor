package com.sirion.searchMicroservice.Controller;


import com.sirion.searchMicroservice.Model.Mentor;
import com.sirion.searchMicroservice.Model.MentorSkill;
import com.sirion.searchMicroservice.Service.MentorService;
import com.sirion.searchMicroservice.Service.MentorSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

class MentorDetails{
    String name;
    long yoe;
    List<String> skill;

    public MentorDetails(String name, long yoe, List<String> skill) {
        this.name = name;
        this.yoe = yoe;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getYoe() {
        return yoe;
    }

    public void setYoe(long yoe) {
        this.yoe = yoe;
    }

    public List<String> getSkill() {
        return skill;
    }

    public void setSkill(List<String> skill) {
        this.skill = skill;
    }
}

@RestController
@RequestMapping
public class MyController {

    @Autowired
    MentorSkillService mentorSkillService;

    @Autowired
    MentorService mentorService;

    //getSearchResults
    @GetMapping(value = "/searchMentor", headers = "Accept=application/json")
    public List<String> searchMentorBySkill(@RequestParam String skill){

        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8961/technology/getTechnologyId";

        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("name",skill).build().toUri();

        ResponseEntity<Long[]> result = restTemplate.getForEntity(uri,Long[].class);


        List<MentorSkill> mentorSkillsList = mentorSkillService.getAllMentorSkill();

        List<String> list = new ArrayList<>();

        for (long skillId : result.getBody()){
//            System.out.println(l);
            for (MentorSkill mentorSkill:mentorSkillsList){
                if(mentorSkill.getSkillId() == skillId){
                    list.add(mentorService.findById(mentorSkill.getId()).getUsername());
                }
            }

        }
        return list;
    }


    //getMentorDetails
    @GetMapping(value = "/getMentorDetails/{mentorId}", headers = "Accept=application/json")
    public ResponseEntity<MentorDetails> getMentorDetails(@PathVariable("mentorId") long mentorId){

        List<String> skills = new ArrayList<>();

        Mentor mentor = mentorService.findById(mentorId);

        if(mentor == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<MentorSkill> mentorSkills = mentorSkillService.getMentorSkills(mentorId);

        if (mentorSkills == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (MentorSkill mentorSkill : mentorSkills){

            RestTemplate restTemplate = new RestTemplate();
            final String baseUrl = "http://localhost:8961/technology/getSkillName";

            URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("skillId", mentorSkill.getSkillId()).build().toUri();

            ResponseEntity<String> result = restTemplate.getForEntity(uri,String.class);

            skills.add(result.getBody());
        }

        return new ResponseEntity<>(new MentorDetails(mentor.getUsername(), mentor.getYearsOfExperience(), skills), HttpStatus.OK);
    }
}
