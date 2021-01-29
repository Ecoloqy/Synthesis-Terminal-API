package eu.ecct.synthesysterminal.account;

import eu.ecct.synthesysterminal.account.entity.Account;
import eu.ecct.synthesysterminal.common.repository.RestApiRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface AccountRepository extends RestApiRepository<Account, UUID> {

    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmail(String email);

}
