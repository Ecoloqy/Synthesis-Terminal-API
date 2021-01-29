package eu.ecct.synthesysterminal.api.controller.validator;

import eu.ecct.synthesysterminal.api.entity.EntityOperations;
import eu.ecct.synthesysterminal.api.entity.account.Account;
import eu.ecct.synthesysterminal.api.entity.account.UserRole;
import eu.ecct.synthesysterminal.api.service.RestApiService;
import eu.ecct.synthesysterminal.api.service.RestApiServiceOperations;
import eu.ecct.synthesysterminal.security.AccountDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

public abstract class RestApiControllerValidator<T extends EntityOperations<V>, V> implements RestApiControllerValidatorOperations<T, V> {

    protected final RestApiService<T, V> service;
    protected final RestApiServiceOperations<Account, UUID> accountService;
    protected final AccountDetailsService accountDetailsService;

    public RestApiControllerValidator(RestApiService<T, V> service,
                                      RestApiServiceOperations<Account, UUID> accountService,
                                      AccountDetailsService accountDetailsService) {
        this.service = service;
        this.accountService = accountService;
        this.accountDetailsService = accountDetailsService;
    }

    @Override
    public boolean isObjectIdEqual(T element, V id) {
        return element.getId().equals(id);
    }

    @Override
    public boolean isExist(V id) {
        return service.isElementExist(id);
    }

    @Override
    public boolean isAdmin() {
        Optional<Account> optionalActualAccount = accountDetailsService.getActiveAccount();
        return optionalActualAccount.map(account -> account.getUserRole().equals(UserRole.ADMIN)).orElse(false);
    }

    @Override
    public void checkIfIsBodyIdEqualsPathId(T element, V id) {
        if (!element.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @Override
    public void checkIfRequestHandledSuccessfully(boolean condition) {
        if (!condition) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
