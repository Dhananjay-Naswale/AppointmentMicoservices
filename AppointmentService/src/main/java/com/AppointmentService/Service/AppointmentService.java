package com.AppointmentService.Service;

import com.AppointmentService.Entity.Appointment;
import com.AppointmentService.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    public Appointment saveAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments()
     {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        Appointment existingAppointment = appointmentRepository.findById(id).orElse(null);
        if (existingAppointment != null) {
            existingAppointment.setPatientId(updatedAppointment.getPatientId());
            existingAppointment.setDoctorId(updatedAppointment.getDoctorId());
            existingAppointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
            // Set other properties as needed
            return appointmentRepository.save(existingAppointment);
        }
        return null;
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
