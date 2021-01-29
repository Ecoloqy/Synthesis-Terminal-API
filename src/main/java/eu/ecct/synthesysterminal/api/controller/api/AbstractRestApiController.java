package eu.ecct.synthesysterminal.api.controller.api;

import eu.ecct.synthesysterminal.api.controller.validator.RestApiControllerValidatorOperations;
import eu.ecct.synthesysterminal.api.entity.EntityOperations;
import eu.ecct.synthesysterminal.api.service.RestApiServiceOperations;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

public abstract class AbstractRestApiController<T extends EntityOperations<V>, V> implements RestApiControllerOperations<T, V> {

    protected final RestApiServiceOperations<T, V> service;
    protected final RestApiControllerValidatorOperations<T, V> validator;

    public AbstractRestApiController(RestApiServiceOperations<T, V> service, RestApiControllerValidatorOperations<T, V> validator) {
        this.service = service;
        this.validator = validator;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<T> getAllElements() {
        return service.getAllElements();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@accountControllerValidator.isOwnerManipulatingWithElement(#id) or hasRole('ADMIN')")
    public T getElement(@PathVariable V id) {
        return service.getElementById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public void saveNewElement(@RequestBody T element) {
        if (!service.addNewElement(element)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@accountControllerValidator.isOwnerManipulatingWithElement(#id) or hasRole('ADMIN')")
    public void updateElement(@RequestBody T element, @PathVariable V id) {
        if (!element.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        if (!service.updateElement(element)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@accountControllerValidator.isOwnerManipulatingWithElement(#id) or hasRole('ADMIN')")
    public void patchElement(@RequestBody T element, @PathVariable V id) {
        if (!element.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        if (!service.patchElement(element, id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@accountControllerValidator.isOwnerManipulatingWithElement(#id) or hasRole('ADMIN')")
    public void deleteElement(@PathVariable V id) {
        if (!service.deleteElement(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
