package com.psoft.healthcareinsuance.Controller;


import com.psoft.healthcareinsuance.Entity.InsuranceEntity;
import com.psoft.healthcareinsuance.Service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurance-companies")
public class InsuranceController {
    @Autowired
    private InsuranceService insuranceService;

    @GetMapping
    public List<InsuranceEntity> getAllInsuranceCompanies() {
        return insuranceService.getAllInsuranceCompanies();
    }

    @GetMapping("/{id}")
    public InsuranceEntity  getInsuranceById(@PathVariable Long id) {
        return insuranceService.getInsuranceById(id);
    }

    @PostMapping
    public InsuranceEntity saveInsurance(@RequestBody InsuranceEntity insuranceCompany) {
        return insuranceService.saveInsurance(insuranceCompany);
    }

    @PutMapping("/{id}")
    public InsuranceEntity updateInsurance(@PathVariable Long id, @RequestBody InsuranceEntity insuranceDetails) {
        return insuranceService.updateInsurance(id, insuranceDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteInsurance(@PathVariable Long id) {
        insuranceService.deleteInsurance(id);
    }
}
