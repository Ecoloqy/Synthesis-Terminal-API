package eu.ecct.server.api.account;

import eu.ecct.server.api.account.entity.Account;
import eu.ecct.server.api.common.service.RestApiServiceOperations;

import java.util.Optional;
import java.util.UUID;

interface AccountServiceOperations extends RestApiServiceOperations<Account, UUID> {

    Optional<Account> getElementByUsername(String username);

    Optional<Account> getElementByEmail(String email);

    Optional<Account> getElementByProviderId(Long providerId);

    boolean isExistsByProviderId(Long providerId);

}
