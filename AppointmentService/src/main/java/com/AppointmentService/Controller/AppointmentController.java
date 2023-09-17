package com.AppointmentService.Controller;

import com.AppointmentService.Entity.Appointment;
import com.AppointmentService.Payload.Doctor;
import com.AppointmentService.Payload.Patient;
import com.AppointmentService.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.client.RestTemplate;

    @RestController
    @RequestMapping("/appointments")
    public class AppointmentController {


        private final RestTemplate restTemplate;
        private AppointmentService appointmentService;

        @Autowired
        public AppointmentController(RestTemplate restTemplate,AppointmentService appointmentService) {
            this.restTemplate = restTemplate;
            this.appointmentService = appointmentService;
        }


        //http://localhost:8082/appointments
        @PostMapping
        public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
            //Invoke Patient microservices to check if thepatient exists
            //ResponseEntity<Patient> patientResponse = restTemplate.getForEntity(
            ResponseEntity<Patient> patientResponse = restTemplate.getForEntity(
                    "http://patient/patients/" + appointment.getPatientId(),
                    Patient.class
            );
            if (patientResponse.getStatusCode() != HttpStatus.OK || patientResponse.getBody() == null) {
                //Handle patient not found error
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            Patient patient = patientResponse.getBody();

            //Invoke Doctor microservices to check if the doctor exists
            ResponseEntity<Doctor> doctorResponse = restTemplate.getForEntity(
                    "http://doctorService/doctors/" + appointment.getDoctorId(),
                    Doctor.class
            );
            if (doctorResponse.getStatusCode() != HttpStatus.OK || doctorResponse.getBody() == null) {
                //Handle patient not found error
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            Doctor doctor = doctorResponse.getBody();

            Appointment newappointment = appointmentService.saveAppointment(appointment);

            return ResponseEntity.status(HttpStatus.CREATED).body(newappointment);
        }
    }


