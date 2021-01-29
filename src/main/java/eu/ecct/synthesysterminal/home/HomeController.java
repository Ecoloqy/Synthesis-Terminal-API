package eu.ecct.synthesysterminal.home;

import eu.ecct.synthesysterminal.security.entity.AccountDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String getLoginPage(@AuthenticationPrincipal AccountDetails details) {
        return details != null ? "index.html" : "login.html";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(@AuthenticationPrincipal AccountDetails details) {
        return details != null ? "index.html" : "registration.html";
    }

}
