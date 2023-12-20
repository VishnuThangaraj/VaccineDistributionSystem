package com.vishnuthangaraj.VaccineDistributionSystem.DTO.Request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PatientLoginDTO {

    private String email;
    private String password;

}
