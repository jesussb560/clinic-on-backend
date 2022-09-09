package com.app.clinicon.appointment;

import java.sql.Timestamp;
import java.util.List;

public interface AppointmentService {
    
    List<Appointment> findAll();
    Appointment findById(long id);
    Appointment save(Appointment appointment);
    long countByStatus(int status);
    long countByStatusAndCreatedAtBetween(int status, Timestamp start, Timestamp end);
}
