package com.libcode.plant.plant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/custom-logout")
    public String logout() {
        String returnTo = "http://localhost:8080/";
        String clientId = "FvGwy7Qw9bPiTFSbHGI3IfEwJQVzX8n8";
        String logoutUrl = "https://dev-wc3lul6afa5rcq0p.us.auth0.com/v2/logout?client_id=" + clientId + "&returnTo=" + returnTo;
        return "redirect:" + logoutUrl;
    }
}
