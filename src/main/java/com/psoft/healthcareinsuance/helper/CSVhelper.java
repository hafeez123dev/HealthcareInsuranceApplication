package com.psoft.healthcareinsuance.helper;

import com.psoft.healthcareinsuance.Entity.PatientEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVhelper {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<PatientEntity> csvToPatients(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<PatientEntity> patientList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                PatientEntity patient = new PatientEntity();
                patient.setName(csvRecord.get("name"));
                patient.setAge(Integer.parseInt(csvRecord.get("age")));
                patient.setCity(csvRecord.get("city"));
                patient.setMedicalHistory(csvRecord.get("medicalHistory"));

                patientList.add(patient);
            }

            return patientList;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }
}
