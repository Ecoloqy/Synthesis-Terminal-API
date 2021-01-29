package eu.ecct.synthesysterminal.api.service.key;

import eu.ecct.synthesysterminal.api.entity.key.Key;
import eu.ecct.synthesysterminal.api.service.RestApiServiceOperations;

import java.util.Optional;
import java.util.UUID;

public interface KeyServiceOperations extends RestApiServiceOperations<Key, Long> {

    Optional<Key> getKeyByValue(UUID value);

}
