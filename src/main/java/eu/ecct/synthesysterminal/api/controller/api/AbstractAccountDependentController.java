package eu.ecct.synthesysterminal.api.controller.api;

import eu.ecct.synthesysterminal.api.controller.validator.RestApiControllerValidatorOperations;
import eu.ecct.synthesysterminal.api.service.RestApiGetByUserOperations;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public abstract class AbstractAccountDependentController<T, V> implements AccountDependentControllerOperations<T, V> {

    protected final RestApiGetByUserOperations<T, V> service;
    protected final RestApiControllerValidatorOperations<T, V> validator;

    public AbstractAccountDependentController(RestApiGetByUserOperations<T, V> service,
                             RestApiControllerValidatorOperations<T, V> validator) {
        this.service = service;
        this.validator = validator;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@accountControllerValidator.isOwnerManipulatingWithElement(#userId) or hasRole('ADMIN')")
    public Iterable<T> getAllElements(@PathVariable UUID userId) {
        return service.getAllElements(userId);
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@accountControllerValidator.isOwnerManipulatingWithElement(#userId) or hasRole('ADMIN')")
    public T getElement(@PathVariable UUID userId, @PathVariable V id) {
        return service.getElementById(userId, id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("@accountControllerValidator.isOwnerManipulatingWithElement(#userId) or hasRole('ADMIN')")
    public void saveNewElement(@RequestBody T element, @PathVariable UUID userId) {
        validator.checkIfRequestHandledSuccessfully(service.addNewElement(element, userId));
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@accountControllerValidator.isOwnerManipulatingWithElement(#userId) or hasRole('ADMIN')")
    public void updateElement(@RequestBody T element, @PathVariable UUID userId, @PathVariable V id) {
        validator.checkIfIsBodyIdEqualsPathId(element, id);
        validator.checkIfRequestHandledSuccessfully(service.updateElement(element));
    }

    @Override
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@accountControllerValidator.isOwnerManipulatingWithElement(#userId) or hasRole('ADMIN')")
    public void patchElement(@RequestBody T element, @PathVariable UUID userId, @PathVariable V id) {
        validator.checkIfIsBodyIdEqualsPathId(element, id);
        validator.checkIfRequestHandledSuccessfully(service.patchElement(element, id));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@accountControllerValidator.isOwnerManipulatingWithElement(#userId) or hasRole('ADMIN')")
    public void deleteElement(@PathVariable UUID userId, @PathVariable V id) {
        validator.checkIfRequestHandledSuccessfully(service.deleteElement(id));
    }

}
