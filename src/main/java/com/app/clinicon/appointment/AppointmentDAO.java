package com.app.clinicon.appointment;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AppointmentDAO extends JpaRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment> {
    
    long countByStatus(int status);
    long countByStatusAndCreatedAtBetween(int status, Timestamp start, Timestamp end);

}
