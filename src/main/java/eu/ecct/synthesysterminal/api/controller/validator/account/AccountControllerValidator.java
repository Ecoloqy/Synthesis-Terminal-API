package eu.ecct.synthesysterminal.api.controller.validator.account;

import eu.ecct.synthesysterminal.api.controller.validator.RestApiControllerValidator;
import eu.ecct.synthesysterminal.api.entity.account.Account;
import eu.ecct.synthesysterminal.api.service.RestApiServiceOperations;
import eu.ecct.synthesysterminal.api.service.account.AccountServiceOperations;
import eu.ecct.synthesysterminal.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AccountControllerValidator extends RestApiControllerValidator<Account, UUID> implements AccountControllerValidatorOperations {

    @Autowired
    public AccountControllerValidator(RestApiServiceOperations<Account, UUID> service, AccountDetailsService accountDetailsService) {
        super(service, service, accountDetailsService);
    }

    @Override
    public boolean isOwnerManipulatingWithElement(UUID id) {
        Optional<Account> optionalActualAccount = accountDetailsService.getActiveAccount();
        return optionalActualAccount.map(account -> account.getId().equals(id)).orElse(false);
    }

    @Override
    public boolean isAddingAble(Account account) {
        AccountServiceOperations accountService = (AccountServiceOperations) service;
        return accountService.getElementByEmail(account.getEmail()).isEmpty()
                && accountService.getElementByUsername(account.getUsername()).isEmpty();
    }

}
