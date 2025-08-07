package com.psoft.healthcareinsuance.Repository;


import com.psoft.healthcareinsuance.Entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    //List<PatientEntity> findByCity(String city);
    List<PatientEntity> findByCity(String city);
}
