package com.psoft.healthcareinsuance.Service;


import com.psoft.healthcareinsuance.Entity.InsuranceEntity;
import com.psoft.healthcareinsuance.Repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceService {
    @Autowired
    private InsuranceRepository insuranceRepository;

    public List<InsuranceEntity> getAllInsuranceCompanies() {
        return insuranceRepository.findAll();
    }

    public InsuranceEntity getInsuranceById(Long id) {
        return insuranceRepository.findById(id).orElse(null);
    }

    public InsuranceEntity saveInsurance(InsuranceEntity insuranceCompany) {
        return insuranceRepository.save(insuranceCompany);
    }

    public InsuranceEntity updateInsurance(Long id, InsuranceEntity insuranceDetails) {
        InsuranceEntity insurance = insuranceRepository.findById(id).orElse(null);
        if (insurance != null) {
            insurance.setName(insuranceDetails.getName());
            insurance.setPolicyDetails(insuranceDetails.getPolicyDetails());
            return insuranceRepository.save(insurance);
        }
        return null;
    }

    public void deleteInsurance(Long id) {
        insuranceRepository.deleteById(id);
    }
}
