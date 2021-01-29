package eu.ecct.synthesysterminal.address;

import eu.ecct.synthesysterminal.common.controller.validator.RestApiControllerValidator;
import eu.ecct.synthesysterminal.account.entity.Account;
import eu.ecct.synthesysterminal.address.entity.Address;
import eu.ecct.synthesysterminal.common.service.RestApiGetByUUIDServiceOperations;
import eu.ecct.synthesysterminal.common.service.RestApiServiceOperations;
import eu.ecct.synthesysterminal.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
class AddressControllerValidator extends RestApiControllerValidator<Address, Long> implements AddressControllerValidatorOperations {

    @Autowired
    public AddressControllerValidator(RestApiGetByUUIDServiceOperations<Address, Long> service,
                                      RestApiServiceOperations<Account, UUID> accountService,
                                      AccountDetailsService accountDetailsService) {
        super(service, accountService, accountDetailsService);
    }

    @Override
    public boolean isOwnerManipulatingWithElement(Long id) {
        Optional<Account> optionalActualAccount = accountDetailsService.getActiveAccount();
        if (optionalActualAccount.isPresent() && optionalActualAccount.get().getAddress() != null) {
            return optionalActualAccount.get().getAddress().getId().equals(id);
        }
        return false;
    }

    @Override
    public boolean isAddingAble(Address address) {
        return true;
    }

}
