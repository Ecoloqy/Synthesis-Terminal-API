package eu.ecct.synthesysterminal.api.service;

public interface RestApiService<T, V> {

    boolean deleteElement(V id);

    boolean isElementExist(V id);

}
