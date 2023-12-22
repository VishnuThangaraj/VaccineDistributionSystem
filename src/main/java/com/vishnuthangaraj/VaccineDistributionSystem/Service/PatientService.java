package com.vishnuthangaraj.VaccineDistributionSystem.Service;

import com.vishnuthangaraj.VaccineDistributionSystem.DTO.Request.PatientLoginDTO;
import com.vishnuthangaraj.VaccineDistributionSystem.DTO.Request.PatientSignupDTO;
import com.vishnuthangaraj.VaccineDistributionSystem.DTO.Response.AppointmentDTO;
import com.vishnuthangaraj.VaccineDistributionSystem.Exceptions.PatientDoesNotExistException;
import com.vishnuthangaraj.VaccineDistributionSystem.Exceptions.WrongCredentials;
import com.vishnuthangaraj.VaccineDistributionSystem.Models.Doctor;
import com.vishnuthangaraj.VaccineDistributionSystem.Models.Patient;
import com.vishnuthangaraj.VaccineDistributionSystem.Models.VaccinationCenter;
import com.vishnuthangaraj.VaccineDistributionSystem.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private VaccinationCenterService vaccinationCenterService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private MailService mailService;

    // Add new Patient to Database (SignUp)
    public Patient signUp(PatientSignupDTO patientSignupDTO){
        Patient patient = new Patient(); // New Patient
        // Set the Data to the Patient
        patient.setName(patientSignupDTO.getName());
        patient.setEmail(patientSignupDTO.getEmail());
        patient.setPassword(patientSignupDTO.getPassword());
        patient.setAadharNumber(patientSignupDTO.getAadharNumber());
        patient.setPhoneNumber(patientSignupDTO.getPhoneNumber());
        patient.setGender(patientSignupDTO.getGender());
        patient.setVaccinationPreference(patientSignupDTO.getVaccinationPreference().toString());
        patient.setAddress(patientSignupDTO.getAddress());

        patientRepository.save(patient); // Add patient to Database

        return patient;
    }

    // LogIn Existing Patient with DTO(email, password)
    // http://localhost:8081/patient/login
    public Patient logIn(PatientLoginDTO patientLoginDTO){
        Patient patient = patientRepository.getPatientByEmail(patientLoginDTO.getEmail());
        if(patient == null){
            throw new PatientDoesNotExistException("Patient email Id is not registered in the Database.");
        }
        if(!patient.getPassword().equals(patientLoginDTO.getPassword())){
            throw new WrongCredentials("Patient Entered Wrong Password.");
        }
        return patient;
    }

    // Create an Appointment for the Patient and return Appointment DTO
    public AppointmentDTO createAppointment(String email, String vaccinationPreference){
        Patient patient = patientRepository.getPatientByEmail(email); // Get Patient by Email
        // Get the list of vaccination centers with the Given preference
        List<VaccinationCenter> vaccinationCenterList = vaccinationCenterService.
                getVaccinationCenterOnPreference(vaccinationPreference, patient.getVaccinationPreference());
        // Assign 0th Index Vaccination Center to the Patient
        VaccinationCenter patientPreference = vaccinationCenterList.get(0);

        // Assign Doctor to the patient who is handling minimum patients
        List<Doctor> doctorList = doctorService.getMinDoctorOnVaccinationCenter(patientPreference.getId());
        Doctor patientDoctor = doctorList.get(0);

        updateDoseCountByOne(patient);

        // Update patient Count for vaccinationCenter and Doctor
        vaccinationCenterService.updatePatientCountByOne(patientPreference);

        doctorService.updatePatientCountByOne(patientDoctor);
        patientDoctor.getPatients().add(patient);
        doctorService.addPatientVsDoctor(patient.getId(), patientDoctor.getId());

        // Create Appointment DTO
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setPatient(patient);
        appointmentDTO.setDocID(patientDoctor.getId());
        appointmentDTO.setDoseNumber(patient.getDoseCount() + 1);
        appointmentDTO.setDoctorName(patientDoctor.getName());
        appointmentDTO.setVaccinationCenterID(patientPreference.getId());
        appointmentDTO.setVaccinationCenterName(patientPreference.getName());

        // Send Mail
        String receiver = patient.getEmail();
        String mailSubject = String.format("Congratulations !! %s your appointment got created ", patient.getName());
        String mailText = String.format("Hii %s," +
                        "\n Your appointment got created. Below are your appointment details :" +
                        "\n1. Dose Count : %d" +
                        "\n2. Doctor Name : %s" +
                        "\n3. Vaccination Center Name  : %s" +
                        "\n4. Vaccination Center Address : %s",
                patient.getName(),
                patient.getDoseCount(),
                patientDoctor.getName(),
                patientPreference.getName(),
                patientPreference.getAddress()
        );
        mailService.generateMail(receiver,mailSubject,mailText);

        return appointmentDTO;
    }

    // Update Patient Dose Count By 1
    public void updateDoseCountByOne(Patient patient){
        UUID id = patient.getId();
        int doseCount = patient.getDoseCount();
        patientRepository.updateDoseCountByOne(id, doseCount);
    }
}
