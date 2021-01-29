package eu.ecct.synthesysterminal.api.repository.key;

import eu.ecct.synthesysterminal.api.entity.key.Key;
import eu.ecct.synthesysterminal.api.repository.RestApiRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface KeyRepository extends RestApiRepository<Key, Long> {

    Optional<Key> findByValue(UUID value);

}
