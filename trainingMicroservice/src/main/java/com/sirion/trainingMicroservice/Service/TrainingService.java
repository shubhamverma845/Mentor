package com.sirion.trainingMicroservice.Service;

import com.sirion.trainingMicroservice.Model.Training;

import java.util.List;

public interface TrainingService {

    void createTraining(Training training);
    Training findById(long id);
    void updateStatusById(long id);
    List<Training> findCompletedTrainingsByUserIdAndApprovedTrue(long id);
    List<Training> findCompletedTrainingsByMentorIdAndApprovedTrue(long mentorId);
    List<Training> findUnderProgressTrainingsByUserIdAndApprovedTrue(long userId);
    List<Training> findUnderProgressTrainingsByMentorIdAndApprovedTrue(long mentorId);
    void finalizeTrainingById(long id);
    List<Training> getAllTrainings();
    List<Training> findNotApprovedTrainingsByUserId(long userId);
    List<Training> findNotApprovedTrainingsByMentorId(long mentorId);
}
