package com.example.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/selectUser")
    public String selectUser(@RequestParam("userType") String userType) {
        if (userType.equals("doctor")) {
            return "doctorOptions";
        } else if (userType.equals("patient")) {
            return "patientOptions";
        } else {

            return "error";
        }
    }


}
