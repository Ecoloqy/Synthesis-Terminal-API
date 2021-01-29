package eu.ecct.synthesysterminal.api.service.account;

import eu.ecct.synthesysterminal.api.entity.account.Account;
import eu.ecct.synthesysterminal.api.service.RestApiServiceOperations;

import java.util.Optional;
import java.util.UUID;

public interface AccountServiceOperations extends RestApiServiceOperations<Account, UUID> {

    Optional<Account> getElementByUsername(String username);

    Optional<Account> getElementByEmail(String email);

}
