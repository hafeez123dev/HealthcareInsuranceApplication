package com.psoft.healthcareinsuance.Service;


import com.psoft.healthcareinsuance.Entity.PatientEntity;
import com.psoft.healthcareinsuance.ExceptionHandling.PatientNotFoundException;
import com.psoft.healthcareinsuance.Repository.PatientRepository;
import com.psoft.healthcareinsuance.helper.CSVhelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<PatientEntity> getAllPatients() {
        return patientRepository.findAll();
    }

    public PatientEntity getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + id + " not found"));
    }

    public PatientEntity savePatient(PatientEntity patient) {
        return patientRepository.save(patient);
    }

    public PatientEntity updatePatient(Long id, PatientEntity patientDetails) {
        PatientEntity patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + id + " not found"));

        patient.setName(patientDetails.getName());
        patient.setAge(patientDetails.getAge());
        patient.setAddress(patientDetails.getAddress());
        patient.setMedicalHistory(patientDetails.getMedicalHistory());
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException("Patient with ID " + id + " not found");
        }
        patientRepository.deleteById(id);
    }
    public void saveCSV(MultipartFile file) {
        try {
            List<PatientEntity> patients = CSVhelper.csvToPatients(file.getInputStream());
            patientRepository.saveAll(patients);
        } catch (Exception e) {
            throw new RuntimeException("Failed to store CSV data: " + e.getMessage());
        }
    }
}