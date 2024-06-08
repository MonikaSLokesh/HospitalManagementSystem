package com.example.hospital.service;

import com.example.hospital.model.Medication;
import com.example.hospital.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    // Method to retrieve all medications
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    // Method to retrieve a medication by its ID
    public Medication getMedicationById(Long id) {
        Optional<Medication> medicationOptional = medicationRepository.findById(id);
        return medicationOptional.orElse(null); // Return null if medication is not found
    }

    // Method to create or update a medication
    public Medication createOrUpdateMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    // Method to delete a medication by its ID
    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }

    // Method to retrieve all medications for a specific patient
    public List<Medication> getAllMedicationsForPatient(Long patientId) {
        return medicationRepository.findByPatient_Id(patientId);
    }


}
