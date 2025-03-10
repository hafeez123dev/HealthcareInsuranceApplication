package com.psoft.healthcareinsuance.Entity;


import jakarta.persistence.*;

@Entity
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(name = "name", length = 100, nullable = false)
    private String name;
    //@Column(name = " age", nullable = false)
    private int age;
   // @Column(name = " address", length = 100, nullable = false)
    private String city;
    //@Column(name = " medical_history", columnDefinition = "TEXT")
    private String medicalHistory;

    // Getters and Setters


    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
