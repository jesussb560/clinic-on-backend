package com.app.clinicon.appointment;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentDAO appointmentDAO;

    @Override
    public List<Appointment> findAll() {
        return appointmentDAO.findAll();
    }

    @Override
    public Appointment findById(long id) {
        return appointmentDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentDAO.save(appointment);
    }

    @Override
    public long countByStatus(int status) {
        return appointmentDAO.countByStatus(status);
    }

    @Override
    public long countByStatusAndCreatedAtBetween(int status, Timestamp start, Timestamp end) {
        return appointmentDAO.countByStatusAndCreatedAtBetween(status, start, end);
    }
    
}
