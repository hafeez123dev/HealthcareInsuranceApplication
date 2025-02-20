package com.psoft.healthcareinsuance.Repository;


import com.psoft.healthcareinsuance.Entity.InsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Long>
{}
