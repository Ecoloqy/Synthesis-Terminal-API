package eu.ecct.synthesysterminal.account;

import eu.ecct.synthesysterminal.common.controller.validator.RestApiControllerValidator;
import eu.ecct.synthesysterminal.account.entity.Account;
import eu.ecct.synthesysterminal.common.service.RestApiServiceOperations;
import eu.ecct.synthesysterminal.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
class AccountControllerValidator extends RestApiControllerValidator<Account, UUID> implements AccountControllerValidatorOperations {

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
