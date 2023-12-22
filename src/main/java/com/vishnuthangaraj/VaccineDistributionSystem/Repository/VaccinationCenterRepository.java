package com.vishnuthangaraj.VaccineDistributionSystem.Repository;

import com.vishnuthangaraj.VaccineDistributionSystem.Models.VaccinationCenter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, UUID> {

    // Return the Vaccination centers With Minimum Doctors
    @Query(value = "select * from vaccination_center where doctor_count = (select min(doctor_count) from vaccination_center)", nativeQuery = true)
    public List<VaccinationCenter> getVaccinationCenterWithMinDoctor();

    // Increase the Doctor Count by 1
    @Modifying
    @Transactional
    @Query(value="update vaccination_center set doctor_count=:doctorCount where id =:id", nativeQuery = true)
    public void increaseDoctorCountByOne(UUID id, int doctorCount);

    // Get all Vaccination Center with Sputnik Count > 0 and Preferred Type
    @Query(value = "select * from vaccination_center where type=:type and sputnik_count != 0 and patient_count = (select min(patient_count) from vaccination_center where type=:type and sputnik_count != 0)", nativeQuery = true)
    public List<VaccinationCenter> getAllVaccinationCenterOnTypeAndSputnikCount(String type);

    // Get all Vaccination Center with Covishield Count > 0 and Preferred Type
    @Query(value = "select * from vaccination_center where type=:type and covishield_count != 0 and patient_count = (select min(patient_count) from vaccination_center where type=:type and covishield_count != 0)", nativeQuery = true)
    public List<VaccinationCenter> getAllVaccinationCenterOnTypeAndCovishieldCount(String type);

    // Get all Vaccination Center with Covaxin Count > 0 and Preferred Type
    @Query(value = "select * from vaccination_center where type=:type and covaxin_count != 0 and patient_count = (select min(patient_count) from vaccination_center where type=:type and covaxin_count != 0)", nativeQuery = true)
    public List<VaccinationCenter> getAllVaccinationCenterOnTypeAndCovaxinCount(String type);

    // Update Vaccination Center By increasing Patient Count By 1
    @Modifying
    @Transactional
    @Query(value = "update vaccination_center set patient_count=:patientCount where id=:id", nativeQuery = true)
    public void updatePatientCountByOne(UUID id, int patientCount);
}
