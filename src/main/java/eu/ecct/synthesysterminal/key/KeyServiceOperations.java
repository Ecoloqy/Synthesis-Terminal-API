package eu.ecct.synthesysterminal.key;

import eu.ecct.synthesysterminal.key.entity.Key;
import eu.ecct.synthesysterminal.common.service.RestApiServiceOperations;

import java.util.Optional;
import java.util.UUID;

interface KeyServiceOperations extends RestApiServiceOperations<Key, Long> {

    Optional<Key> getKeyByValue(UUID value);

}
