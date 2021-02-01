package eu.ecct.server.api.address;

import eu.ecct.server.api.account.entity.Account;
import eu.ecct.server.api.address.entity.Address;
import eu.ecct.server.api.common.repository.RestApiRepository;
import eu.ecct.server.api.common.service.AbstractRestApiGetByUUIDService;
import eu.ecct.server.api.common.service.RestApiServiceOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
class AddressService extends AbstractRestApiGetByUUIDService<Address, Long> implements AddressServiceOperations {

    @Autowired
    public AddressService(RestApiRepository<Address, Long> repository,
                          RestApiServiceOperations<Account, UUID> accountService) {
        super(repository, accountService);
    }

    @Override
    public Iterable<Address> getAllElements(UUID userId) {
        Optional<Account> account = accountService.getElementById(userId);
        if (account.isPresent() && account.get().getAddress() != null) {
            return Collections.singletonList(account.get().getAddress());
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Address> getElementById(UUID userId, Long id) {
        Optional<Account> account = accountService.getElementById(userId);
        if (account.isPresent() && account.get().getAddress() != null) {
            return Optional.of(account.get().getAddress());
        }
        return Optional.empty();
    }

    @Override
    public void addNewElement(Address address, UUID userId) {
        Account account = accountService.getElementById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        account.setAddress(address);
        accountService.updateElement(account);
    }

    @Override
    public void updateElement(Address address) {
        repository.save(address);
    }

    @Override
    public void patchElement(Address address, Long id) {
        Address oldAddress = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (address.getCity() != null) oldAddress.setCity(address.getCity());
        if (address.getCountry() != null) oldAddress.setCountry(address.getCountry());
        if (address.getStreet() != null) oldAddress.setStreet(address.getStreet());
        if (address.getZipCode() != null) oldAddress.setZipCode(address.getZipCode());
        repository.save(oldAddress);
    }

    @Override
    public void deleteElement(Long id) {
        Address address = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        address.setActive(false);
        repository.save(address);
    }

}
