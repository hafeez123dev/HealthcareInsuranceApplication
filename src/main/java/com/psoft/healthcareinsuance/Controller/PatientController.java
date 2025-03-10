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
    @Cacheable(value = "patients")
    public List<PatientEntity> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "patients", key = "#id")
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
    @DeleteMapping("/cache/{id}")
    @CacheEvict(value = "patients", key = "#id")
    public ResponseEntity<String> evictPatientCache(@PathVariable Long id) {
        return ResponseEntity.ok("The patient with ID " + id + " removed successfully.");
    }
    @DeleteMapping("/cache")
    @CacheEvict(value = "patients", allEntries = true )
    public ResponseEntity<String> evictAllPatientsCache() {
        return ResponseEntity.ok("All patients cached data removed successfully.");
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
    @GetMapping("/city")
   // @Cacheable(value = "patientsByCity", key = "#cityName")
    public List<PatientEntity> getPatientsByCity(@RequestParam String cityName) {
        return patientService.getPatientsByCity(cityName);
    }

}