package com.vishnuthangaraj.VaccineDistributionSystem.Service;

import com.vishnuthangaraj.VaccineDistributionSystem.Models.VaccinationCenter;
import com.vishnuthangaraj.VaccineDistributionSystem.Repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VaccinationCenterService {

    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;

    // Register/Add Vaccination Center to the Database
    public VaccinationCenter registerVaccinationCenter(VaccinationCenter vaccinationCenter){
        vaccinationCenterRepository.save(vaccinationCenter);
        return vaccinationCenter;
    }

    // Get Vaccination Center with the Minimum Doctor Count
    public List<VaccinationCenter> getVaccinationCenterWithMinDoctor(){
        List<VaccinationCenter> vaccinationCenters =
                vaccinationCenterRepository.getVaccinationCenterWithMinDoctor();
        return vaccinationCenters;
    }

    // Increase the count of Doctor By-1 in the given Vaccination Center
    public void increaseDoctorCountByOne(VaccinationCenter vaccinationCenter){
        UUID id = vaccinationCenter.getId();
        int doctorCount = vaccinationCenter.getDoctorCount()+1;
        vaccinationCenterRepository.increaseDoctorCountByOne(id, doctorCount);
    }

    // Get the list of vaccination centers with the Given preference
    public List<VaccinationCenter> getVaccinationCenterOnPreference
        (String vaccinationCenterPreference, String vaccinationPreference){
        if(vaccinationPreference.equals("Sputnik")){
            return vaccinationCenterRepository.getAllVaccinationCenterOnTypeAndSputnikCount(vaccinationCenterPreference);
        }
        else if(vaccinationPreference.equals("Covishield")){
            return vaccinationCenterRepository.getAllVaccinationCenterOnTypeAndCovishieldCount(vaccinationCenterPreference);
        }
        return vaccinationCenterRepository.getAllVaccinationCenterOnTypeAndCovaxinCount(vaccinationCenterPreference);
    }

    // Update Vaccination Center By increasing Patient Count By 1
    public void updatePatientCountByOne(VaccinationCenter vaccinationCenter){
        UUID id = vaccinationCenter.getId();
        int patientCount = vaccinationCenter.getPatientCount() + 1;
        vaccinationCenterRepository.updatePatientCountByOne(id, patientCount);
    }
}
