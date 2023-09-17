package com.patient.Service;

import com.patient.Entity.Patient;
import com.patient.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {

        return patientRepository.findById(id).orElse(null);
    }

    public Patient createPatient(Patient patient) {

        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existingPatient = patientRepository.findById(id).orElse(null);
        if (existingPatient != null) {
            existingPatient.setName(updatedPatient.getName());
            existingPatient.setAge(updatedPatient.getAge());
            // Set other properties as needed
            return patientRepository.save(existingPatient);
        }
        return null;
    }

    public void deletePatient(Long id) {

          patientRepository.deleteById(id);
    }
}
