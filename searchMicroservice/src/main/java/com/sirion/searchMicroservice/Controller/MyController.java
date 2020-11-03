package com.sirion.searchMicroservice.Controller;


import com.sirion.searchMicroservice.Model.Mentor;
import com.sirion.searchMicroservice.Model.MentorDetails;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping
public class MyController {

    Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    MentorSkillService mentorSkillService;

    @Autowired
    MentorService mentorService;


    //getSearchResults
    @GetMapping(value = "/searchMentor", headers = "Accept=application/json")
    public List<String> searchMentorBySkill(@RequestParam String skill){

        Long[] skills = getTechnologiesByName(skill);
        List<MentorSkill> mentorSkillsList = mentorSkillService.getAllMentorSkill();
        List<String> list = new ArrayList<>();

        for(MentorSkill mentorSkill:mentorSkillsList){
            if(Arrays.asList(skills).contains(mentorSkill.getSkillId())){

                Mentor mentor = mentorService.findById(mentorSkill.getMentorId());

                list.add(mentor.getUsername() + ", MID:" + mentor.getId());
            }
        }

        return list;
    }


    //getMentorDetails
    @GetMapping(value = "/getMentorDetails/{mentorId}", headers = "Accept=application/json")
    public ResponseEntity<MentorDetails> getMentorDetails(@PathVariable("mentorId") long mentorId){

        List<String> skills = new ArrayList<>();

        try {
            Mentor mentor = mentorService.findById(mentorId);
            List<MentorSkill> mentorSkills = mentorSkillService.getMentorSkills(mentorId);

            mentorSkills.stream()
                    .map(mentorSkill -> getTechnologyName(mentorSkill.getSkillId()) + ", ID:" + mentorSkill.getSkillId())
                    .forEach(skills::add);

            return new ResponseEntity<>(new MentorDetails(mentor.getId(), mentor.getUsername(),
                    mentor.getYearsOfExperience(),
                    skills), HttpStatus.OK);

        } catch (Exception e){
            logger.warn("Invalid Mentor ID");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //get technologies by name
    public Long[] getTechnologiesByName(String skill){

        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8961/technology/getTechnologyId";
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("name",skill).build().toUri();
        ResponseEntity<Long[]> result = restTemplate.getForEntity(uri,Long[].class);
        return result.getBody();
    }


    //get technology name
    public String getTechnologyName(long skillId){

        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8961/technology/getSkillName";
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("skillId",skillId).build().toUri();
        ResponseEntity<String> result = restTemplate.getForEntity(uri,String.class);
        return result.getBody();
    }
}