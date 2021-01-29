package eu.ecct.synthesysterminal.api.service.address;

import eu.ecct.synthesysterminal.api.entity.account.Account;
import eu.ecct.synthesysterminal.api.entity.address.Address;
import eu.ecct.synthesysterminal.api.entity.device.Device;
import eu.ecct.synthesysterminal.api.repository.RestApiRepository;
import eu.ecct.synthesysterminal.api.service.AbstractApiGetByUserOperations;
import eu.ecct.synthesysterminal.api.service.AbstractRestApiService;
import eu.ecct.synthesysterminal.api.service.RestApiServiceOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService extends AbstractApiGetByUserOperations<Address, Long> implements AddressServiceOperations {

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
    public boolean addNewElement(Address address, UUID userId) {
        Optional<Account> accountOptional = accountService.getElementById(userId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setAddress(address);
            accountService.updateElement(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateElement(Address address) {
        Optional<Address> oldAddressOptional = repository.findById(address.getId());
        if (oldAddressOptional.isPresent()) {
            Address oldAddress = oldAddressOptional.get();
            oldAddress.setCity(address.getCity());
            oldAddress.setCountry(address.getCountry());
            oldAddress.setStreet(address.getStreet());
            oldAddress.setZipCode(address.getZipCode());
            oldAddress.setArchived(address.isArchived());
            repository.save(oldAddress);
            return true;
        }
        return false;
    }

    @Override
    public boolean patchElement(Address address, Long id) {
        Optional<Address> oldAddressOptional = repository.findById(id);
        if (oldAddressOptional.isPresent()) {
            Address oldAddress = oldAddressOptional.get();
            if (address.getCity() != null) oldAddress.setCity(address.getCity());
            if (address.getCountry() != null) oldAddress.setCountry(address.getCountry());
            if (address.getStreet() != null) oldAddress.setStreet(address.getStreet());
            if (address.getZipCode() != null) oldAddress.setZipCode(address.getZipCode());
            repository.save(oldAddress);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteElement(Long id) {
        Optional<Address> oldAddressOptional = repository.findById(id);
        if (oldAddressOptional.isPresent()) {
            Address oldAddress = oldAddressOptional.get();
            oldAddress.setArchived(true);
            repository.save(oldAddress);
            return true;
        }
        return false;
    }

}
