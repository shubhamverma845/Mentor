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
    public List<Training> findCompletedTrainingsByUserIdAndApprovedTrue(long id) {
        return trainingRepository.findByUserIdAndUnderProgressFalseAndApprovedTrue(id);
    }

    @Override
    public List<Training> findCompletedTrainingsByMentorIdAndApprovedTrue(long mentorId) {
        return trainingRepository.findByMentorIdAndUnderProgressFalseAndApprovedTrue(mentorId);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public void finalizeTrainingById(long id) {
        trainingRepository.updateUnderProgressById(id);
    }

    @Override
    public List<Training> findUnderProgressTrainingsByUserIdAndApprovedTrue(long userId) {
        return trainingRepository.findByUserIdAndUnderProgressTrueAndApprovedTrue(userId);
    }

    @Override
    public List<Training> findNotApprovedTrainingsByUserId(long userId) {
        return  trainingRepository.findByUserIdAndApprovedFalse(userId);
    }

    @Override
    public List<Training> findNotApprovedTrainingsByMentorId(long mentorId) {
        return trainingRepository.findByMentorIdAndApprovedFalse(mentorId);
    }

    @Override
    public List<Training> findUnderProgressTrainingsByMentorIdAndApprovedTrue(long mentorId) {
        return trainingRepository.findByMentorIdAndUnderProgressTrueAndApprovedTrue(mentorId);
    }
}
