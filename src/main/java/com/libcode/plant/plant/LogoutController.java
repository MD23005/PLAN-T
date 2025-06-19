package com.libcode.plant.plant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/custom-logout")
    public String logout() {
        String returnTo = "http://https://plan-t-rol7.onrender.com/";
        String clientId = "sbj7qQLLPsUFrNliyCs6ZmDZ8olbDP99";
        String logoutUrl = "https://dev-qi8b5nrabbvawh0y.us.auth0.com/v2/logout?client_id=" + clientId + "&returnTo=" + returnTo;
        return "redirect:" + logoutUrl;
    }
}
