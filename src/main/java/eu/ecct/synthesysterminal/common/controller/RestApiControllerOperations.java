package eu.ecct.synthesysterminal.common.controller;

public interface RestApiControllerOperations<T, V> {

    Iterable<T> getAllElements();

    T getElement(V id);

    void saveNewElement(T element);

    void updateElement(T element, V id);

    void patchElement(T element, V id);

    void deleteElement(V id);

}
