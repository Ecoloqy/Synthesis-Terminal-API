package eu.ecct.synthesysterminal.api.controller.validator.address;

import eu.ecct.synthesysterminal.api.controller.validator.RestApiControllerValidator;
import eu.ecct.synthesysterminal.api.entity.account.Account;
import eu.ecct.synthesysterminal.api.entity.address.Address;
import eu.ecct.synthesysterminal.api.service.RestApiGetByUserOperations;
import eu.ecct.synthesysterminal.api.service.RestApiService;
import eu.ecct.synthesysterminal.api.service.RestApiServiceOperations;
import eu.ecct.synthesysterminal.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AddressControllerValidator extends RestApiControllerValidator<Address, Long> implements AddressControllerValidatorOperations {

    @Autowired
    public AddressControllerValidator(RestApiGetByUserOperations<Address, Long> service,
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
