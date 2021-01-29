package eu.ecct.synthesysterminal.account;

import eu.ecct.synthesysterminal.common.controller.AbstractRestApiController;
import eu.ecct.synthesysterminal.common.controller.validator.RestApiControllerValidatorOperations;
import eu.ecct.synthesysterminal.account.entity.Account;
import eu.ecct.synthesysterminal.common.service.RestApiServiceOperations;
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

    @Autowired
    public AccountController(RestApiServiceOperations<Account, UUID> service,
                             RestApiControllerValidatorOperations<Account, UUID> validator) {
        super(service, validator);
    }

}
