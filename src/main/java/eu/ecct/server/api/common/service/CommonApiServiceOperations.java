package eu.ecct.server.api.common.service;

public interface CommonApiServiceOperations<T, V> {

    void updateElement(T element);

    void patchElement(T element, V id);

    void deleteElement(V id);

    boolean isElementExist(V id);

}
