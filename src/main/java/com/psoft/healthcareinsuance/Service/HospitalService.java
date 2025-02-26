package com.psoft.healthcareinsuance.Service;


import com.psoft.healthcareinsuance.Entity.HospitalEntity;
import com.psoft.healthcareinsuance.Repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    public List<HospitalEntity> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public HospitalEntity getHospitalById(Long id) {
        return hospitalRepository.findById(id).orElse(null);
    }

    public HospitalEntity saveHospital(HospitalEntity hospital) {
        return hospitalRepository.save(hospital);
    }

    public HospitalEntity updateHospital(Long id, HospitalEntity hospitalDetails) {
        HospitalEntity hospital = hospitalRepository.findById(id).get();
        if (Optional.ofNullable(hospital).isEmpty()) return null;
        hospital.setName(hospitalDetails.getName());
        hospital.setLocation(hospitalDetails.getLocation());
        return hospitalRepository.save(hospital);
    }

    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }
}
