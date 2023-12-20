package com.vishnuthangaraj.VaccineDistributionSystem.Service;

import com.vishnuthangaraj.VaccineDistributionSystem.Models.Doctor;
import com.vishnuthangaraj.VaccineDistributionSystem.Models.VaccinationCenter;
import com.vishnuthangaraj.VaccineDistributionSystem.Repository.DoctorRepository;
import com.vishnuthangaraj.VaccineDistributionSystem.Repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private VaccinationCenterService vaccinationCenterService;

    // Assign Doctor to the Vaccination Center with minimum Doctors
    public Doctor registerDoctor(Doctor doctor){
        // Get the list of Vaccination centers with min doctors
        List<VaccinationCenter> vaccinationCenters =
                vaccinationCenterService.getVaccinationCenterWithMinDoctor();
        VaccinationCenter vaccinationCenter = vaccinationCenters.get(0);

        // Set Vaccination Center on the 0th index to the Doctor
        doctor.setVaccinationCenter(vaccinationCenter);

        // Increase the Doctor count in the vaccinationCenter
        vaccinationCenterService.increaseDoctorCountByOne(vaccinationCenter);

        doctorRepository.save(doctor); // Save Doctor to the Database
        return doctor;
    }

    // Get List of Doctors on Basis of VaccinationCenter
    public List<Doctor> getMinDoctorOnVaccinationCenter(UUID vaccinationCenterID){
        return doctorRepository.getMinDoctorOnVaccinationCenter(vaccinationCenterID);
    }

    // Update Doctor By Increasing Patient Count by 1
    public void updatePatientCountByOne(Doctor doctor){
        UUID id = doctor.getId();
        int patientCount = doctor.getPatientCount() + 1;
        doctorRepository.updatePatientCountByOne(id, patientCount);
    }

    // Insert patient into doctor vs patient table
    public void addPatientVsDoctor(UUID patientId, UUID doctorId){
        doctorRepository.addPatientVsDoctor(patientId,doctorId);
    }
}
