package com.psoft.healthcareinsuance.Controller;



import com.psoft.healthcareinsuance.Entity.PatientEntity;
import com.psoft.healthcareinsuance.Service.PatientService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importPatientJob;

    @GetMapping
    @Cacheable(value = "patients")
    public List<PatientEntity> getAllPatients() {
        log.info("Fetching all patients...");
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "patients", key = "#id")
    public PatientEntity getPatientById(@PathVariable Long id) {
        log.debug("Fetching patient with ID: {}", id);
        return patientService.getPatientById(id);
    }

    @PostMapping
    public PatientEntity savePatient(@RequestBody PatientEntity patient) {
        log.info("Saving new patient: {}", patient.getName());
        return patientService.savePatient(patient);
    }

    @PutMapping("/{id}")
    public PatientEntity updatePatient(@PathVariable Long id, @RequestBody PatientEntity patientDetails) {
        return patientService.updatePatient(id, patientDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        log.warn("Deleting patient with ID: {}", id);
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
        return ResponseEntity.ok("File uploaded successfully: " );
    }
    @GetMapping("/city")
    @Cacheable(value = "patientsByCity", key = "#cityName")
    public List<PatientEntity> getPatientsByCity(@RequestParam String cityName) {
        return patientService.getPatientsByCity(cityName);
    }
    @PostMapping("/upload-batch")
    public ResponseEntity<String> uploadCSVBatch(@RequestParam("file") MultipartFile file) {
        try {
            // Step 1: Define the directory to save the file - use absolute path
            String directoryPath = "C:/Users/Md Abdul Hafeez/Desktop";  // Use an absolute path to save the file
            File dir = new File(directoryPath);

            // Step 2: Create the directory if it doesn't exist
            if (!dir.exists()) {
                dir.mkdirs();  // This will create the "uploads" directory if it doesn't already exist
            }

            // Step 3: Define the file path where the file will be saved
            String filePath = directoryPath + file.getOriginalFilename();  // Construct the complete file path
            File convFile = new File(filePath);  // Create a file object using the file path

            // Step 4: Transfer the file to the specified location
            file.transferTo(convFile);  // Save the uploaded file to the specified path

            // Step 5: Pass the file path to the Spring Batch job parameters
            JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
            jobParametersBuilder.addString("filePath", filePath);  // Pass the file path to the batch job
            jobParametersBuilder.addLong("time", System.currentTimeMillis());  // Add timestamp for uniqueness
            var jobParameters = jobParametersBuilder.toJobParameters();

            // Step 6: Run the batch job with the file path parameter
            jobLauncher.run(importPatientJob, jobParameters);
            return ResponseEntity.ok("Batch job triggered successfully.");

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Batch job failed: " + e.getMessage());
        }
    }



}

