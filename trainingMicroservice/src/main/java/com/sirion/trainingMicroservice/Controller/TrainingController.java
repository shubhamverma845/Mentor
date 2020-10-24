package com.sirion.trainingMicroservice.Controller;


import com.sirion.trainingMicroservice.Model.Training;
import com.sirion.trainingMicroservice.Service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/training")
public class TrainingController {

    @Autowired
    TrainingService trainingService;

    @PostMapping(value = "/createTraining", headers = "Accept=application/json")
    public void createTraining(Training training){

//        System.out.println(training.getMentorId());
        trainingService.createTraining(training);
    }

    @PostMapping(value = "/proposeTraining", headers = "Accept=application/json")
    public Training proposeTraining(@RequestParam long userId,
                                    @RequestParam long mentorId,
                                    @RequestParam long skillId){
        Training training = new Training();
        training.setMentorId(mentorId);
        training.setSkillId(skillId);
        training.setUserId(userId);

        trainingService.createTraining(training);

        return training;
    }

    @PutMapping(value = "/approveTraining/{id}", headers = "Accept=application/json")
    public ResponseEntity<Void> approveTraining(@PathVariable("id") long id){
        if (trainingService.findById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        trainingService.updateStatusById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping(value = "/getTraining/{id}", headers = "Accept=application/json")
    public Training getTrainingById(@PathVariable("id") long id){
        return trainingService.findById(id);
    }

    @GetMapping(value = "/getCompletedTrainingsByUserId/{userId}", headers = "Accept=application/json")
    public ResponseEntity<List<Training>> getCompletedTrainingsByUserId(@PathVariable("userId") long userId){

        List<Training> trainings = trainingService.findCompletedTrainingsByUserId(userId);

        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }

    @GetMapping(value = "/getCompletedTrainingsByMentorId/{mentorId}", headers = "Accept=application/json")
    public ResponseEntity<List<Training>> getCompletedTrainingsByMentorId(@PathVariable("mentorId") long mentorId){

        List<Training> trainings = trainingService.findCompletedTrainingsByMentorId(mentorId);

        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }

    @GetMapping(value = "/getUnderProgressTrainingsByUserId/{userId}", headers = "Accept=application/json")
    public ResponseEntity<List<Training>> getUnderProgressTrainingsByUserId(@PathVariable("userId") long userId){

        List<Training> trainings = trainingService.findUnderProgressTrainingsByUserId(userId);

        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }

    @GetMapping(value = "/getUnderProgressTrainingsByMentorId/{mentorId}", headers = "Accept=application/json")
    public ResponseEntity<List<Training>> getUnderProgressTrainingsByMentorId(@PathVariable("mentorId") long mentorId){

        List<Training> trainings = trainingService.findUnderProgressTrainingsByMentorId(mentorId);

        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }

    @PutMapping(value = "/finalizeTraining/{id}", headers = "Accept=application/json")
    public ResponseEntity<Training> finalizeTraining(@PathVariable("id") long id){

        Training training = trainingService.findById(id);
        if(training == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        trainingService.finalizeTrainingById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
