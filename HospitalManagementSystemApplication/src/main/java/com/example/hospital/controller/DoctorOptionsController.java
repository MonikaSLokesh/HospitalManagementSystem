package com.example.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DoctorOptionsController {

    @GetMapping("/doctors/submitOption")
    public ModelAndView submitOption(@RequestParam("doctorOption") String doctorOption) {
        if (doctorOption.equals("yes")) {
            return new ModelAndView("redirect:/doctors/new");
        } else if (doctorOption.equals("no")) {
            return new ModelAndView("doctorOptionsNo");
        }

        return new ModelAndView("redirect:/doctors/view");
    }
}
