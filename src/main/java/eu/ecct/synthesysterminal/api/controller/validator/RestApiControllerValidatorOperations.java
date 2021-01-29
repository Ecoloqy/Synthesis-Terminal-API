package eu.ecct.synthesysterminal.api.controller.validator;

import java.util.UUID;

public interface RestApiControllerValidatorOperations<T, V> {

    boolean isObjectIdEqual(T element, V id);

    boolean isOwnerManipulatingWithElement(V id);

    boolean isExist(V id);

    boolean isAdmin();

    boolean isAddingAble(T element);

    void checkIfIsBodyIdEqualsPathId(T object, V id);

    void checkIfRequestHandledSuccessfully(boolean condition);

}
