package eu.ecct.synthesysterminal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomepage() {
        return "index.html";
    }

    @GetMapping("/dashboard")
    public String getDashboard() {
        return "dashboard.html";
    }

    @GetMapping("/settings")
    public String getSettings() {
        return "settings.html";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about.html";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

}
