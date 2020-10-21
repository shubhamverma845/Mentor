package com.sirion.searchMicroservice.Service;


import com.sirion.searchMicroservice.Model.MentorSkill;
import com.sirion.searchMicroservice.Repository.MentorSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorSkillServiceImp implements MentorSkillService {

    @Autowired
    MentorSkillRepository mentorSkillRepository;

    @Override
    public void createMentorSkill(MentorSkill mentorSkill) {
        mentorSkillRepository.save(mentorSkill);
    }

    @Override
    public void deleteMentorSkillById(long id) {
        mentorSkillRepository.deleteById(id);
    }

    @Override
    public MentorSkill updateMentorSkill(MentorSkill mentorSkill) {
        return mentorSkillRepository.save(mentorSkill);
    }

    @Override
    public MentorSkill findById(long id) {
        return mentorSkillRepository.findById(id).get();
    }

    @Override
    public List<MentorSkill> getAllMentorSkill() {
        return mentorSkillRepository.findAll();
    }

    @Override
    public void updateRating(MentorSkill mentorSkill) {
        mentorSkillRepository.save(mentorSkill);
    }

    @Override
    public List<MentorSkill> getMentorSkills(long mentorId) {
        return mentorSkillRepository.findByMentorId(mentorId);
    }
}
