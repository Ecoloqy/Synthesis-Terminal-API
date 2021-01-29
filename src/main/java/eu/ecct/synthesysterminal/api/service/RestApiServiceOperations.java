package eu.ecct.synthesysterminal.api.service;

import java.util.Optional;

public interface RestApiServiceOperations<T, V> extends RestApiService<T, V> {

    Iterable<T> getAllElements();

    Optional<T> getElementById(V id);

    boolean addNewElement(T element);

    boolean updateElement(T element);

    boolean patchElement(T element, V id);

}
