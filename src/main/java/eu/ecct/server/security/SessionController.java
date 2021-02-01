package eu.ecct.server.security;

import eu.ecct.server.api.account.AccountService;
import eu.ecct.server.api.account.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class SessionController {

    private final AccountService accountService;

    @Autowired
    public SessionController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/me")
    public Account getActiveAccount(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) return null;
        return accountService.getElementById(principal.getAttribute("id")).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
