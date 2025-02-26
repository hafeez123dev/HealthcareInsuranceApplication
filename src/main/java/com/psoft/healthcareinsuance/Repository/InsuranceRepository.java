package com.psoft.healthcareinsuance.Repository;


import com.psoft.healthcareinsuance.Entity.InsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Long>
{}
