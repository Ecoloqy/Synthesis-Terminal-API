package eu.ecct.synthesysterminal.api.repository.account;

import eu.ecct.synthesysterminal.api.entity.account.Account;
import eu.ecct.synthesysterminal.api.repository.RestApiRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends RestApiRepository<Account, UUID> {

    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmail(String email);

}
