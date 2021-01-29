package eu.ecct.synthesysterminal.account;

import eu.ecct.synthesysterminal.account.entity.Account;
import eu.ecct.synthesysterminal.common.service.RestApiServiceOperations;

import java.util.Optional;
import java.util.UUID;

interface AccountServiceOperations extends RestApiServiceOperations<Account, UUID> {

    Optional<Account> getElementByUsername(String username);

    Optional<Account> getElementByEmail(String email);

}
