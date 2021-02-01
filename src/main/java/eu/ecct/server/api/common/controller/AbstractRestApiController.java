package eu.ecct.server.api.common.controller;

import eu.ecct.server.api.common.entity.EntityOperations;
import eu.ecct.server.api.common.service.RestApiServiceOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

public abstract class AbstractRestApiController<T extends EntityOperations<V>, V> implements RestApiControllerOperations<T, V> {

    protected final RestApiServiceOperations<T, V> service;

    public AbstractRestApiController(RestApiServiceOperations<T, V> service) {
        this.service = service;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<T> getAllElements() {
        return service.getAllElements();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public T getElement(@PathVariable V id) {
        return service.getElementById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNewElement(@RequestBody T element) {
        service.addNewElement(element);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateElement(@RequestBody T element, @PathVariable V id) {
        if (!element.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        service.updateElement(element);
    }

    @Override
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchElement(@RequestBody T element, @PathVariable V id) {
        if (!element.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        service.patchElement(element, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteElement(@PathVariable V id) {
        service.deleteElement(id);
    }

}
