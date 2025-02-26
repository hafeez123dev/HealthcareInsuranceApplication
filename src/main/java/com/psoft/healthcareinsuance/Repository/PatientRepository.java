package com.psoft.healthcareinsuance.Repository;


import com.psoft.healthcareinsuance.Entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

}
