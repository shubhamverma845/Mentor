package com.sirion.trainingMicroservice.Service;

import com.sirion.trainingMicroservice.Model.Training;

import java.util.List;

public interface TrainingService {
    void createTraining(Training training);
    Training findById(long id);
    void updateStatusById(long id);
    List<Training> findCompletedTrainingsByUserId(long id);
    List<Training> findCompletedTrainingsByMentorId(long mentorId);

    List<Training> findUnderProgressTrainingsByUserId(long userId);

    List<Training> findUnderProgressTrainingsByMentorId(long mentorId);
}
