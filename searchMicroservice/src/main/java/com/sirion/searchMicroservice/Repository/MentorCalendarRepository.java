package com.sirion.searchMicroservice.Repository;

import com.sirion.searchMicroservice.Model.MentorCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorCalendarRepository extends JpaRepository<MentorCalendar, Long> {
}
