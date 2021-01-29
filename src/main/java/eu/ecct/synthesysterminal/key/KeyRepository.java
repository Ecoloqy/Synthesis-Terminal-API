package eu.ecct.synthesysterminal.key;

import eu.ecct.synthesysterminal.key.entity.Key;
import eu.ecct.synthesysterminal.common.repository.RestApiRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface KeyRepository extends RestApiRepository<Key, Long> {

    Optional<Key> findByValue(UUID value);

}
