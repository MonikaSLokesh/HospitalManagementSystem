package com.example.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PatientOptionsController {

    @GetMapping("/patients/submitOption")
    public ModelAndView submitOption(@RequestParam("patientOption") String patientOption) {
        if (patientOption.equals("yes")) {
            return new ModelAndView("redirect:/patients/new");
        } else
            return new ModelAndView("patientOptionsNo");

    }
    @GetMapping("/options")
    public String showPatientOptions(@RequestParam("id") Long patientId) {
        // Redirect to the page that displays patient options using the patientId
        return "redirect:/patients/options?id=" + patientId;
    }

}
