package com.vishnuthangaraj.VaccineDistributionSystem.DTO.Request;

import com.vishnuthangaraj.VaccineDistributionSystem.Enums.VaccinationCenterPreference;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PatientSignupDTO {

    private String name;
    private String email;
    private String password;
    private String aadharNumber;
    private long phoneNumber;
    private String gender;
    private VaccinationCenterPreference vaccinationPreference;
    private String address;

}
