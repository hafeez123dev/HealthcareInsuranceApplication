package com.psoft.healthcareinsuance.Batch;

import com.psoft.healthcareinsuance.Entity.PatientEntity;
import com.psoft.healthcareinsuance.Repository.PatientRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientWriter implements ItemWriter<PatientEntity> {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void write(Chunk<? extends PatientEntity> chunk) throws Exception {
        System.out.println("⏳ Writing chunk of size: " + chunk.size());
        for (PatientEntity p : chunk) {
            System.out.println("✅ Writing patient: " + p.getName());
        }
        patientRepository.saveAll(chunk);
    }
}
