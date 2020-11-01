package com.sirion.trainingMicroservice.Controller;


import com.sirion.trainingMicroservice.Model.Payment;
import com.sirion.trainingMicroservice.Model.Training;
import com.sirion.trainingMicroservice.Service.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/training")
public class TrainingController {

    private Logger logger = LoggerFactory.getLogger(TrainingController.class);

    @Autowired
    TrainingService trainingService;

    //get all trainings
    @GetMapping(value = "/getAllTrainings", headers = "Accept=application/json")
    public ResponseEntity<List<Training>> getAllTrainings(){

        return new ResponseEntity<>(trainingService.getAllTrainings(), HttpStatus.OK);
    }


    //create training
    @PostMapping(value = "/createTraining", headers = "Accept=application/json")
    public ResponseEntity<String> createTraining(Training training){

        trainingService.createTraining(training);
        return new ResponseEntity<>("Training Created with Training ID:" + training.getId(), HttpStatus.CREATED);
    }


    //propose training
    @PostMapping(value = "/proposeTraining", headers = "Accept=application/json")
    public ResponseEntity<String> proposeTraining(@RequestParam long userId,
                                    @RequestParam long mentorId,
                                    @RequestParam long skillId){

        Training training = new Training();
        training.setMentorId(mentorId);
        training.setSkillId(skillId);
        training.setUserId(userId);

        trainingService.createTraining(training);

        return new ResponseEntity<>("Training Proposed with training ID:" + training.getId(), HttpStatus.CREATED);
    }


    //approve training
    @PutMapping(value = "/approveTraining/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> approveTraining(@PathVariable("id") long id){

        try {
            trainingService.findById(id);
            trainingService.updateStatusById(id);
            return new ResponseEntity<>("Training Approved", HttpStatus.OK);
        } catch (Exception e){
            logger.warn("Invalid Training ID");
            return new ResponseEntity<>("Invalid Training Id", HttpStatus.NOT_FOUND);
        }
    }


    //complete training
    @PutMapping(value = "/finalizeTraining/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> finalizeTraining(@PathVariable("id") long id){

        try {
            Training training = trainingService.findById(id);
            trainingService.finalizeTrainingById(id);

            //sending payment details
            String paymentId = doPayment(training.getId(),
                    training.getUserId(),
                    training.getMentorId(),
                    training.getId(),
                    42069);

            return new ResponseEntity<>("Training Completed\n" + "Payment ID::" + paymentId, HttpStatus.OK);
        } catch (NoSuchElementException e){
            logger.warn("Invalid Training ID");
            return new ResponseEntity<>("Invalid Training Id", HttpStatus.NOT_FOUND);
        } catch (Exception e){
            logger.warn("Connection refused from Payment Portal");
            return new ResponseEntity<>("Connection refused from payment portal!!", HttpStatus.BAD_GATEWAY);
        }
    }

    //get training through id
    @GetMapping(value = "/getTraining/{id}", headers = "Accept=application/json")
    public ResponseEntity<Training> getTrainingById(@PathVariable("id") long id){

        try {
            Training training = trainingService.findById(id);
            return new ResponseEntity<>(training, HttpStatus.OK);
        } catch (Exception e){
            logger.warn("Invalid Training Id");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //get completed trainings by userId
    @GetMapping(value = "/getCompletedTrainingsByUserId/{userId}", headers = "Accept=application/json")
    public ResponseEntity<List<Training>> getCompletedTrainingsByUserId(@PathVariable("userId") long userId){
        List<Training> trainings = trainingService.findCompletedTrainingsByUserId(userId);
        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }


    //get completed trainings by mentorId
    @GetMapping(value = "/getCompletedTrainingsByMentorId/{mentorId}", headers = "Accept=application/json")
    public ResponseEntity<List<Training>> getCompletedTrainingsByMentorId(@PathVariable("mentorId") long mentorId){

        List<Training> trainings = trainingService.findCompletedTrainingsByMentorId(mentorId);
        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }


    //get under progress trainings by userId
    @GetMapping(value = "/getUnderProgressTrainingsByUserId/{userId}", headers = "Accept=application/json")
    public ResponseEntity<List<Training>> getUnderProgressTrainingsByUserId(@PathVariable("userId") long userId){

        List<Training> trainings = trainingService.findUnderProgressTrainingsByUserId(userId);
        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }


    //get under progress trainings by mentorId
    @GetMapping(value = "/getUnderProgressTrainingsByMentorId/{mentorId}", headers = "Accept=application/json")
    public ResponseEntity<List<Training>> getUnderProgressTrainingsByMentorId(@PathVariable("mentorId") long mentorId){

        List<Training> trainings = trainingService.findUnderProgressTrainingsByMentorId(mentorId);
        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }


    //payment function
    public String doPayment(long id, long userId, long mentorId, long tid, long amount){

        logger.info("Processing payment for Training ID:" + tid);

        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8965/payment/createPayment";

        URI uri =  null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Payment payment = new Payment(Long.toString(id),
                Long.toString(userId),
                Long.toString(mentorId),
                Long.toString(tid),
                Long.toString(amount));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Payment> entity = new HttpEntity<>(payment,headers);

        assert uri != null;
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        return result.getBody();
    }
}
