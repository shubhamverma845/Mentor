package com.sirion.searchMicroservice.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.*;
import java.util.Date;


@Entity
@DynamicInsert
@Table(name = "MentorCalendarTable")
public class MentorCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    long mentorId;

    @Column(columnDefinition = "date default current_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    Date startDate;

    @Column(columnDefinition = "date default current_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    Date endDate;

    @Column(columnDefinition = "time default current_time(0)")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="HH:mm:ss")
    Date startTime;

    @Column(columnDefinition = "time default current_time(0)")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="HH:mm:ss")
    Date endTime;

    public MentorCalendar() {
    }

    public MentorCalendar(Date startDate, Date endDate, Date startTime, Date endTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMentorId() {
        return mentorId;
    }

    public void setMentorId(long mentorId) {
        this.mentorId = mentorId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
