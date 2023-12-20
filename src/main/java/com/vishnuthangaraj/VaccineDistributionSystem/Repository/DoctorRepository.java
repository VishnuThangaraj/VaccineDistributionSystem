package com.vishnuthangaraj.VaccineDistributionSystem.Repository;

import com.vishnuthangaraj.VaccineDistributionSystem.Models.Doctor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    // Get List of Doctors on Basis of VaccinationCenter
    @Query(value = "select * from doctor where vaccination_center_id=:vaccinationCenterID and patient_count = (select min(patient_count) from doctor where vaccination_center_id=:vaccinationCenterID)", nativeQuery = true)
    public List<Doctor> getMinDoctorOnVaccinationCenter(UUID vaccinationCenterID);

    // Update Doctor By Increasing Patient Count by 1
    @Modifying
    @Transactional
    @Query(value = "update doctor set patient_count=:patientCount where id=:id", nativeQuery = true)
    public void updatePatientCountByOne(UUID id, int patientCount);

    // Insert patient into doctor vs patient table
    @Modifying
    @Transactional
    @Query(value = "insert into doctor_patient (doctor_id, patient_id) values (:doctorId, :patientId)", nativeQuery = true)
    public void addPatientVsDoctor(UUID patientId, UUID doctorId);
}
