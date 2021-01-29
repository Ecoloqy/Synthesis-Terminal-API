package eu.ecct.synthesysterminal.common.service;

import java.util.Optional;

public interface RestApiServiceOperations<T, V> extends CommonApiServiceOperations<T, V> {

    Iterable<T> getAllElements();

    Optional<T> getElementById(V id);

    void addNewElement(T element);

}
