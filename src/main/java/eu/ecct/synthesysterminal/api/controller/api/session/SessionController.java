package eu.ecct.synthesysterminal.api.controller.api.session;

import eu.ecct.synthesysterminal.api.component.security.AccountFactory;
import eu.ecct.synthesysterminal.api.component.security.AccountValidator;
import eu.ecct.synthesysterminal.api.entity.account.Account;
import eu.ecct.synthesysterminal.api.service.account.AccountService;
import eu.ecct.synthesysterminal.security.security.register.RegisterDetails;
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
    private final AccountFactory accountFactory;

    @Autowired
    public SessionController(AccountService accountService,
                             AccountValidator accountValidator,
                             AccountFactory accountFactory) {
        this.accountService = accountService;
        this.accountValidator = accountValidator;
        this.accountFactory = accountFactory;
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

        Account account = accountFactory.createNewLocalAccount(details);
        accountService.addNewElement(account);
        return Collections.singletonMap("details", "Successfully created!");
    }

    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout() {

    }

}

