package com.vishnuthangaraj.VaccineDistributionSystem.Controller;

import com.vishnuthangaraj.VaccineDistributionSystem.DTO.Request.PatientLoginDTO;
import com.vishnuthangaraj.VaccineDistributionSystem.DTO.Request.PatientSignupDTO;
import com.vishnuthangaraj.VaccineDistributionSystem.DTO.Response.AppointmentDTO;
import com.vishnuthangaraj.VaccineDistributionSystem.DTO.Response.GeneralMessageDTO;
import com.vishnuthangaraj.VaccineDistributionSystem.Enums.VaccinationCenterPrefrence;
import com.vishnuthangaraj.VaccineDistributionSystem.Exceptions.PatientDoesNotExistException;
import com.vishnuthangaraj.VaccineDistributionSystem.Exceptions.WrongCredentials;
import com.vishnuthangaraj.VaccineDistributionSystem.Models.Patient;
import com.vishnuthangaraj.VaccineDistributionSystem.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Add new Patient to the Database
    @PostMapping("/signup")
    public ResponseEntity<Patient> signUp(@RequestBody PatientSignupDTO patientSignupDTO){
        Patient patient = patientService.signUp(patientSignupDTO);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    // LogIn Existing Patient with DTO(email, password)
    @PostMapping("/login")
    public ResponseEntity logIn(@RequestBody PatientLoginDTO patientLoginDTO){
        try{
            Patient patient = patientService.logIn(patientLoginDTO);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        }
        catch (WrongCredentials wrongCredentials){
            return new ResponseEntity<>(new GeneralMessageDTO(wrongCredentials.getMessage()), HttpStatus.UNAUTHORIZED);
        }
        catch (PatientDoesNotExistException patientDoesNotExistException){
            return new ResponseEntity<>(new GeneralMessageDTO(patientDoesNotExistException.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    // Create an Appointment
    @GetMapping("/create-appointment")
    public ResponseEntity<AppointmentDTO> createAppointment
    (@RequestParam String email, @RequestParam VaccinationCenterPrefrence vaccinationCenterPreference){
        AppointmentDTO appointment = patientService.createAppointment(email, vaccinationCenterPreference.toString());
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }
}
