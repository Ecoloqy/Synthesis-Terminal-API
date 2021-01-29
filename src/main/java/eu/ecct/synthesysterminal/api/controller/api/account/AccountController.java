package eu.ecct.synthesysterminal.api.controller.api.account;

import eu.ecct.synthesysterminal.api.controller.api.AbstractRestApiController;
import eu.ecct.synthesysterminal.api.controller.validator.RestApiControllerValidatorOperations;
import eu.ecct.synthesysterminal.api.entity.account.Account;
import eu.ecct.synthesysterminal.api.service.RestApiServiceOperations;
import eu.ecct.synthesysterminal.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class AccountController extends AbstractRestApiController<Account, UUID> implements AccountControllerOperations {

    private final AccountDetailsService accountDetailsService;

    @Autowired
    public AccountController(RestApiServiceOperations<Account, UUID> service,
                             RestApiControllerValidatorOperations<Account, UUID> validator,
                             AccountDetailsService accountDetailsService) {
        super(service, validator);
        this.accountDetailsService = accountDetailsService;
    }

    @Override
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getActiveAccount() {
        Account account = accountDetailsService.getActiveAccount().orElse(null);
        return Collections.singletonMap("details", account);
    }

}
