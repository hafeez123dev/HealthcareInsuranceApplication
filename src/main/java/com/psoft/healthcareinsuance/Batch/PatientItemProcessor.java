package com.psoft.healthcareinsuance.Batch;

import com.psoft.healthcareinsuance.Entity.PatientEntity;
import org.springframework.batch.item.ItemProcessor;

public class PatientItemProcessor implements ItemProcessor<PatientEntity, PatientEntity> {
    @Override
    public PatientEntity process(PatientEntity patient) throws Exception {
        // You can add any custom validation or transformation here
        patient.setName(patient.getName().toUpperCase());
        return patient;
    }
}

