package com.vishnuthangaraj.VaccineDistributionSystem.Repository;

import com.vishnuthangaraj.VaccineDistributionSystem.Models.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    // Get the patient using the emailId
    @Query(value = "select * from patient where email =:patientEmail", nativeQuery = true)
    public Patient getPatientByEmail(String patientEmail);

    // Update Patient Dose Count By 1
    @Transactional
    @Modifying
    @Query(value = "update patient set dose_count=:doseCount where id=:id", nativeQuery = true)
    public void updateDoseCountByOne(UUID id, int doseCount);
}
