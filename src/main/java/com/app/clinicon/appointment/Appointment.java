package com.app.clinicon.appointment;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.app.clinicon.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code;
    
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    @DateTimeFormat(pattern = "HH:mm")
    private Time time;
    
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    private User doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    private User patient;

    @PrePersist
    private void prePersist(){

        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTimeInMillis());

        this.createdAt = now;
        this.updatedAt = null;
        this.status = 1;

    }

    @PreUpdate
    private void preUpdate(){

        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTimeInMillis());
        
        this.updatedAt = now;

    }
    
}
