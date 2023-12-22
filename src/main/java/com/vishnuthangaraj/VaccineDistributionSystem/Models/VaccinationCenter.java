package com.vishnuthangaraj.VaccineDistributionSystem.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VaccinationCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String type; // private or public
    private String address;
    private int covaxinCount;
    private int covishieldCount;
    private int sputnikCount;
    private int patientCount;
    private int doctorCount;

    @OneToMany(mappedBy = "vaccinationCenter")
    private List<Doctor> doctors;
}
