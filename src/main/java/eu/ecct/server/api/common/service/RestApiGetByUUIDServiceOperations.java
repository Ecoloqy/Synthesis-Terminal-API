package eu.ecct.server.api.common.service;

import java.util.Optional;
import java.util.UUID;

public interface RestApiGetByUUIDServiceOperations<T, V> extends CommonApiServiceOperations<T, V> {

    Iterable<T> getAllElements(UUID userId);

    Optional<T> getElementById(UUID userId, V id);

    void addNewElement(T element, UUID userId);

}
