package com.vishnuthangaraj.VaccineDistributionSystem.DTO.Response;

import com.vishnuthangaraj.VaccineDistributionSystem.Models.Patient;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AppointmentDTO {
    private int doseNumber;
    private Patient patient;
    private UUID docID;
    private String doctorName;
    private UUID vaccinationCenterID;
    private String vaccinationCenterName;
}
