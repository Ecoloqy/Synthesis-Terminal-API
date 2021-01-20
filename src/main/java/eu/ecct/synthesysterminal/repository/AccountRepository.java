package eu.ecct.synthesysterminal.repository;

import eu.ecct.synthesysterminal.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<Account, UUID> {

    Optional<Account> findByUsername(String username);

}
