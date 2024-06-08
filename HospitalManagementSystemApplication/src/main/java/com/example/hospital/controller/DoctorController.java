package com.example.hospital.controller;

import com.example.hospital.model.Doctor;
import com.example.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/options")
    public String showDoctorOptions(@RequestParam(name = "id") Long doctorId, Model model) {
        // Check if doctorId is not null or empty
        if (doctorId != null) {
            // If doctorId is available, add it to the model
            model.addAttribute("doctorId", doctorId);
        }

        return "oldDoctor"; // You need to define this view appropriately
    }

    @GetMapping
    public String listDoctors(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctor/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Doctor> doctorOptional = doctorService.getDoctorById(id);
        if (doctorOptional.isPresent()) {
            model.addAttribute("doctor", doctorOptional.get());
        } else {
            throw new RuntimeException("Doctor not found");
        }
        return "doctor/edit";
    }

    @PostMapping("/edit/{id}")
    public String editDoctor(@PathVariable Long id, @ModelAttribute Doctor doctorDetails) {
        doctorService.updateDoctor(id, doctorDetails);
        return "redirect:/doctors/" + id;
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctor/create";
    }

    @PostMapping("/new")
    public String createDoctor(@ModelAttribute Doctor doctor) {
        doctorService.createDoctor(doctor);
        return "redirect:/doctors";
    }

    @PostMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }
}
