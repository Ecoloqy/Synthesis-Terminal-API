package eu.ecct.synthesysterminal.api.service;

import java.util.Optional;
import java.util.UUID;

public interface RestApiGetByUserOperations<T, V> extends RestApiService<T, V> {

    Iterable<T> getAllElements(UUID userId);

    Optional<T> getElementById(UUID userId, V id);

    boolean addNewElement(T element, UUID userId);

    boolean updateElement(T element);

    boolean patchElement(T element, V id);

}
