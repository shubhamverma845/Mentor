package com.sirion.searchMicroservice.Service;

import com.sirion.searchMicroservice.Model.MentorCalendar;

import java.util.List;

public interface MentorCalendarService {
    void createMentorCalendar(MentorCalendar mentorCalendar);
    void deleteMentorCalendar(long id);
    MentorCalendar findById(long id);
    MentorCalendar updateMentorCalendar(MentorCalendar mentorCalendar);
    List<MentorCalendar> getAllMentorCalendar();
}