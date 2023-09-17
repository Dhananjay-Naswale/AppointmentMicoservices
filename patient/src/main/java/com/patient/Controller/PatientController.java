package com.patient.Controller;

import com.patient.Entity.Patient;
import com.patient.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    //http://localhost:8080/patients/1
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id)
    {
        return patientService.getPatientById(id);
    }
//http://localhost:8080/patients
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient)
    {
        return patientService.createPatient(patient);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        return patientService.updatePatient(id, updatedPatient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {

         patientService.deletePatient(id);
    }
}
