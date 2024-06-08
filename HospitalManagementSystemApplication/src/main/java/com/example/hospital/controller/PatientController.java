package com.example.hospital.controller;

import com.example.hospital.model.Patient;
import com.example.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/options")
    public String showPatientOptions(@RequestParam(name = "id") Long patientId, Model model) {
        // Check if patientId is not null or empty
        if (patientId != null) {
            // If patientId is available, add it to the model
            model.addAttribute("patientId", patientId);
        }

        return "oldPatient";
    }

    @GetMapping
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patient/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/create";
    }

    @PostMapping("/new")
    public String createPatient(@ModelAttribute Patient patient) {
        patientService.createPatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/{id}")
    public String viewPatient(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found")));
        return "patient/view";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found")));
        return "patient/edit";
    }
    @PostMapping("/{id}/edit")
    public String editPatient(@PathVariable Long id, @ModelAttribute Patient updatedPatient) {
        patientService.updatePatient(id, updatedPatient);
        return "redirect:/patients/edit/{patientId}";
    }

    @GetMapping("/{id}/appointments")
    public String viewAppointments(@PathVariable Long id, Model model) {
        return "patient/appointments";
    }

    @GetMapping("/{id}/medications")
    public String viewMedications(@PathVariable Long id, Model model) {
        return "patient/medications";
    }
}
