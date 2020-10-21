package com.sirion.searchMicroservice.Repository;


import com.sirion.searchMicroservice.Model.MentorSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorSkillRepository extends JpaRepository<MentorSkill, Long> {

    List<MentorSkill> findBySkillId(long id);
    List<MentorSkill> findByMentorId(long id);

}
