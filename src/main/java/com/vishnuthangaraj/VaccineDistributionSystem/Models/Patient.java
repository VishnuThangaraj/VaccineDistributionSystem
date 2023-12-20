package com.vishnuthangaraj.VaccineDistributionSystem.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String gender;
    private int doseCount;
    private String vaccinationPreference;
    private String address;

    @Column(unique = true)
    private String aadharNumber;

    @Column(unique = true)
    private long phoneNumber;

    @Column(unique = true)
    private String email;
    private String password;

}
