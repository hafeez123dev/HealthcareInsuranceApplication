package com.psoft.healthcareinsuance.Controller;


import com.psoft.healthcareinsuance.Entity.HospitalEntity;
import com.psoft.healthcareinsuance.Service.HospitalService;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.misc.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public List<HospitalEntity> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/{id}")
    public HospitalEntity getHospitalById(@PathVariable Long id) {
        return hospitalService.getHospitalById(id);
    }

    @PostMapping
    public HospitalEntity saveHospital(@RequestBody HospitalEntity hospital) {
        return hospitalService.saveHospital(hospital);
    }

    @PutMapping("/{id}")
    public HospitalEntity updateHospital(@PathVariable Long id, @RequestBody HospitalEntity hospitalDetails) {
        return hospitalService.updateHospital(id, hospitalDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteHospital(@PathVariable Long id) {
        hospitalService.deleteHospital(id);
    }




}
