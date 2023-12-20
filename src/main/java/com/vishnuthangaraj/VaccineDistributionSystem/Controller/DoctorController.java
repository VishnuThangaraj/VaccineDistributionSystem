package com.vishnuthangaraj.VaccineDistributionSystem.Controller;

import com.vishnuthangaraj.VaccineDistributionSystem.Models.Doctor;
import com.vishnuthangaraj.VaccineDistributionSystem.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Add new Doctor to the Database
    @PostMapping("/register")
    public ResponseEntity<Doctor> registerDoctor(@RequestBody Doctor doctorObj){
        Doctor doctor = doctorService.registerDoctor(doctorObj);
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }
}
