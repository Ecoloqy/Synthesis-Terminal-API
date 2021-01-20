package eu.ecct.synthesysterminal.controller;

import eu.ecct.synthesysterminal.entity.Account;
import eu.ecct.synthesysterminal.security.model.AccountDetails;
import eu.ecct.synthesysterminal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class AuthController {

    private final AccountService accountService;

    @Autowired
    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/auth/logged")
    public Map<String, Object> getLoggedUser(Authentication authentication) {
        if (authentication == null) return Collections.singletonMap("details", null);
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
        Account account = accountService.getAccountById(accountDetails.getUserId());
        return Collections.singletonMap("details", account);
    }

}
