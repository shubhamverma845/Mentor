package com.sirion.trainingMicroservice.Repository;

import com.sirion.trainingMicroservice.Model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long>{

    @Transactional
    @Modifying
    @Query(value = "UPDATE training_table SET approved = TRUE, under_progress = TRUE, start_date = CURRENT_TIMESTAMP WHERE id = ?1", nativeQuery = true)
    void updateStatusById(long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE training_table SET under_progress = FALSE, end_date = CURRENT_TIMESTAMP WHERE id = ?1", nativeQuery = true)
    void updateUnderProgressById(long id);


    List<Training> findByUserIdAndUnderProgressFalseAndApprovedTrue(long userId);
    List<Training> findByMentorIdAndUnderProgressFalseAndApprovedTrue(long mentorId);
    List<Training> findByUserIdAndUnderProgressTrueAndApprovedTrue(long userId);
    List<Training> findByMentorIdAndUnderProgressTrueAndApprovedTrue(long mentorId);
    List<Training> findByUserIdAndApprovedFalse(long userId);
    List<Training> findByMentorIdAndApprovedFalse(long mentorID);
}
