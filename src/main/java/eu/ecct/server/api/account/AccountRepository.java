package eu.ecct.server.api.account;

import eu.ecct.server.api.account.entity.Account;
import eu.ecct.server.api.common.repository.RestApiRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface AccountRepository extends RestApiRepository<Account, UUID> {

    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmail(String email);

    Optional<Account> findByProviderId(Long providerId);

    boolean existsByProviderId(Long providerId);

}
