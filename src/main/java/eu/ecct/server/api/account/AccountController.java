package eu.ecct.server.api.account;

import eu.ecct.server.api.account.entity.Account;
import eu.ecct.server.api.common.controller.AbstractRestApiController;
import eu.ecct.server.api.common.service.RestApiServiceOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class AccountController extends AbstractRestApiController<Account, UUID> implements AccountControllerOperations {

    @Autowired
    public AccountController(RestApiServiceOperations<Account, UUID> service) {
        super(service);
    }

}
