package eu.ecct.server.security;

import eu.ecct.server.api.account.AccountService;
import eu.ecct.server.api.account.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class SessionController {

    private final AccountService accountService;

    @Autowired
    public SessionController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/api/login/github")
    public String getLoginPage() {
        return "redirect:/oauth2/authorization/github";
    }

    @ResponseBody
    @GetMapping("/api/me")
    public Account getActiveAccount(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) return null;
        Long providerId = ((Integer) principal.getAttributes().get("id")).longValue();
        return accountService.getElementByProviderId(providerId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
