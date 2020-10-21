package com.sirion.searchMicroservice.Service;


import com.sirion.searchMicroservice.Model.Mentor;

import java.util.List;

public interface MentorService {
    void createMentor(Mentor mentor);
    void deleteMentorById(long id);
    Mentor findById(long id);
    Mentor updateMentor(Mentor mentor);
    List<Mentor> getAllMentor();
}
