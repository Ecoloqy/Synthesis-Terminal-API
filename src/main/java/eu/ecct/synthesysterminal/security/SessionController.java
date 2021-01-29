package eu.ecct.synthesysterminal.security;

import eu.ecct.synthesysterminal.security.AccountValidator;
import eu.ecct.synthesysterminal.account.entity.Account;
import eu.ecct.synthesysterminal.account.AccountService;
import eu.ecct.synthesysterminal.security.entity.RegisterDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class SessionController {

    private final AccountService accountService;
    private final AccountValidator accountValidator;
    private final AccountDetailsService accountDetailsService;

    @Autowired
    public SessionController(AccountService accountService, AccountValidator accountValidator, AccountDetailsService accountDetailsService) {
        this.accountService = accountService;
        this.accountValidator = accountValidator;
        this.accountDetailsService = accountDetailsService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login() {

    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> handleRegistration(@RequestBody RegisterDetails details, BindingResult bindingResult) {
        accountValidator.validate(details, bindingResult);
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                return Collections.singletonMap("details", error.getCode());
            }
        }

//        Account account = new Account(details.getUsername(), );
//        accountService.addNewElement(account);
        return Collections.singletonMap("details", "Successfully created!");
    }

    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout() {

    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getActiveAccount() {
        Account account = accountDetailsService.getActiveAccount().orElse(null);
        return Collections.singletonMap("details", account);
    }

}

