package eu.ecct.server.arduino.key;

import eu.ecct.server.arduino.key.entity.Key;
import eu.ecct.server.api.common.service.RestApiServiceOperations;

import java.util.Optional;
import java.util.UUID;

interface KeyServiceOperations {

    Iterable<Key> getAllKeys();

    Optional<Key> getKeyById(Long id);

    void addOrUpdateKey(Key key);

    void deleteKey(Long id);

    Optional<Key> getKeyByValue(UUID value);

}
