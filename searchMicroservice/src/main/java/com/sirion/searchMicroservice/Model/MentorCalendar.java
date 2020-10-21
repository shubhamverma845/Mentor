package com.sirion.searchMicroservice.Model;


import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@DynamicInsert
@Table(name = "MentorCalendarTable")
public class MentorCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mentorId;


}
