package com.sirion.searchMicroservice.Service;


import com.sirion.searchMicroservice.Model.MentorSkill;

import java.util.List;

public interface MentorSkillService {
    void createMentorSkill(MentorSkill mentorSkill);
    void deleteMentorSkillById(long id);
    MentorSkill updateMentorSkill(MentorSkill mentorSkill);
    MentorSkill findById(long id);
    List<MentorSkill> getAllMentorSkill();
    void updateRating(MentorSkill mentorSkill);

    List<MentorSkill> getMentorSkills(long mentorId);
}
