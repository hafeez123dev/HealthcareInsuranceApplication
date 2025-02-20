package com.psoft.healthcareinsuance.Controller;


import com.psoft.healthcareinsuance.Entity.PatientEntity;
import com.psoft.healthcareinsuance.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<PatientEntity> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public PatientEntity getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @PostMapping
    public PatientEntity savePatient(@RequestBody PatientEntity patient) {
        return patientService.savePatient(patient);
    }

    @PutMapping("/{id}")
    public PatientEntity updatePatient(@PathVariable Long id, @RequestBody PatientEntity patientDetails) {
        return patientService.updatePatient(id, patientDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
}