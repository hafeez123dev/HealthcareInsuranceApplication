package com.psoft.healthcareinsuance.Repository;

import com.psoft.healthcareinsuance.Entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalEntity, Long>
{

}

