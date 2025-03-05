package com.psoft.healthcareinsuance.Controller;


import com.psoft.healthcareinsuance.Entity.PatientEntity;
import com.psoft.healthcareinsuance.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
   // @Cacheable(value = "patients")
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
    @CacheEvict(value = "patients", allEntries = true)
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint.";
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        return "This is a secured endpoint. You must be authenticated.";
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a CSV file");
        }

        if (!file.getContentType().equals("text/csv")) {
            return ResponseEntity.badRequest().body("Only CSV files are allowed");
        }

        patientService.saveCSV(file);
        return ResponseEntity.ok("File Uploaded Successfully!");
    }
}