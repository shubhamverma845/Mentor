package com.sirion.searchMicroservice.Service;

import com.sirion.searchMicroservice.Model.MentorCalendar;
import com.sirion.searchMicroservice.Repository.MentorCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MentorCalendarServiceImp implements MentorCalendarService {


    @Autowired
    MentorCalendarRepository mentorCalendarRepository;

    @Override
    public void createMentorCalendar(MentorCalendar mentorCalendar) {
        mentorCalendarRepository.save(mentorCalendar);
    }

    @Override
    public void deleteMentorCalendar(long id) {
        mentorCalendarRepository.deleteById(id);
    }

    @Override
    public MentorCalendar findById(long id) {
        return mentorCalendarRepository.findById(id).get();
    }

    @Override
    public MentorCalendar updateMentorCalendar(MentorCalendar mentorCalendar) {
        return mentorCalendarRepository.save(mentorCalendar);
    }

    @Override
    public List<MentorCalendar> getAllMentorCalendar() {
        return mentorCalendarRepository.findAll();
    }
}
