package com.vishnuthangaraj.VaccineDistributionSystem.Controller;

import com.vishnuthangaraj.VaccineDistributionSystem.Models.VaccinationCenter;
import com.vishnuthangaraj.VaccineDistributionSystem.Service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaccination-center")
public class VaccinationCenterController {

    @Autowired
    private VaccinationCenterService vaccinationCenterService;

    // Add new Vaccination Center to Database
    @PostMapping("/register")
    public ResponseEntity<VaccinationCenter> register(@RequestBody VaccinationCenter vaccinationCenterObj){
        VaccinationCenter vaccinationCenter =
                vaccinationCenterService.registerVaccinationCenter(vaccinationCenterObj);
        return new ResponseEntity<>(vaccinationCenter, HttpStatus.CREATED);
    }
}
