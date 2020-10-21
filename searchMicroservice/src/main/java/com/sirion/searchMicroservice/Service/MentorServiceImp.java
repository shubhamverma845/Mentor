package com.sirion.searchMicroservice.Service;


import com.sirion.searchMicroservice.Model.Mentor;
import com.sirion.searchMicroservice.Repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorServiceImp implements MentorService {

    @Autowired
    MentorRepository mentorRepository;

    @Override
    public void createMentor(Mentor mentor) {
        mentorRepository.save(mentor);
    }

    @Override
    public void deleteMentorById(long id) {
        mentorRepository.deleteById(id);
    }

    @Override
    public Mentor findById(long id) {
        return mentorRepository.findById(id).get();
    }

    @Override
    public Mentor updateMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    @Override
    public List<Mentor> getAllMentor() {
        return mentorRepository.findAll();
    }
}
