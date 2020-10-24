package com.sirion.trainingMicroservice.Service;

import com.sirion.trainingMicroservice.Model.Training;
import com.sirion.trainingMicroservice.Repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    TrainingRepository trainingRepository;

    @Override
    public void createTraining(Training training) {
        trainingRepository.save(training);
    }

    @Override
    public Training findById(long id) {
        return trainingRepository.findById(id).get();
    }

    @Override
    public void updateStatusById(long id) {
        trainingRepository.updateStatusById(id);
    }

    @Override
    public List<Training> findCompletedTrainingsByUserId(long id) {
        return trainingRepository.findByUserIdAndUnderProgressFalse(id);
    }

    @Override
    public List<Training> findCompletedTrainingsByMentorId(long mentorId) {
        return trainingRepository.findByMentorIdAndUnderProgressFalse(mentorId);
    }

    @Override
    public void finalizeTrainingById(long id) {
        trainingRepository.updateUnderProgressById(id);
    }

    @Override
    public List<Training> findUnderProgressTrainingsByUserId(long userId) {
        return trainingRepository.findByUserIdAndUnderProgressTrue(userId);
    }

    @Override
    public List<Training> findUnderProgressTrainingsByMentorId(long mentorId) {
        return trainingRepository.findByMentorIdAndUnderProgressTrue(mentorId);
    }
}
