package eu.ecct.synthesysterminal.common.controller;

import java.util.UUID;

public interface AccountDependentControllerOperations<T, V> {

    Iterable<T> getAllElements(UUID userId);

    T getElement(UUID userId, V id);

    void saveNewElement(T element, UUID userId);

    void updateElement(T element, UUID userId, V id);

    void patchElement(T element, UUID userId, V id);

    void deleteElement(UUID userId, V id);

}
