package eu.ecct.synthesysterminal.api.controller.api;

public interface RestApiControllerOperations<T, V> {

    Iterable<T> getAllElements();

    T getElement(V id);

    void saveNewElement(T element);

    void updateElement(T element, V id);

    void patchElement(T element, V id);

    void deleteElement(V id);

}
