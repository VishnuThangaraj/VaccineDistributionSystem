package com.vishnuthangaraj.VaccineDistributionSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String docDegree;
    private int patientCount;

    @ManyToOne
    @JsonIgnore
    private VaccinationCenter vaccinationCenter;

    @ManyToMany
    private List<Patient> patients;
}
