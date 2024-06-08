package com.example.hospital.controller;

import com.example.hospital.model.Medication;
import com.example.hospital.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medications")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping
    public String listMedications(Model model) {
        model.addAttribute("medications", medicationService.getAllMedications());
        return "medication/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("medication", new Medication());
        return "medication/create";
    }

    @PostMapping("/new")
    public String createMedication(@ModelAttribute("medication") Medication medication) {
        medicationService.createOrUpdateMedication(medication);
        return "redirect:/medications";
    }

    @GetMapping("/{id}")
    public String viewMedication(@PathVariable Long id, Model model) {
        Medication medication = medicationService.getMedicationById(id);
        if (medication == null) {
            // Medication not found, handle the case appropriately
            return "redirect:/medications";
        }
        model.addAttribute("medication", medication);
        return "medication/view";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Medication medication = medicationService.getMedicationById(id);
        if (medication == null) {
            // Medication not found, handle the case appropriately
            return "redirect:/medications";
        }
        model.addAttribute("medication", medication);
        return "medication/edit";
    }

    @PostMapping("/{id}/edit")
    public String editMedication(@PathVariable Long id, @ModelAttribute("medication") Medication medication) {
        medication.setId(id); // Ensure the ID is set for update
        medicationService.createOrUpdateMedication(medication);
        return "redirect:/medications";
    }

    @PostMapping("/{id}/delete")
    public String deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
        return "redirect:/medications";
    }
}
