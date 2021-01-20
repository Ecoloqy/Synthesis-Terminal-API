package eu.ecct.synthesysterminal.service;

import eu.ecct.synthesysterminal.entity.Account;
import eu.ecct.synthesysterminal.entity.Address;
import eu.ecct.synthesysterminal.repository.AccountRepository;
import eu.ecct.synthesysterminal.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Iterable<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public Address saveAddress(Address address) {
        Optional<Address> oldAddressOptional = addressRepository.findById(address.getId());
        if (oldAddressOptional.isPresent()) {
            Address oldAddress = oldAddressOptional.get();

            oldAddress.setCountry(address.getCountry());
            oldAddress.setCity(address.getCity());
            oldAddress.setStreet(address.getStreet());
            oldAddress.setZipCode(address.getZipCode());

            return addressRepository.save(oldAddress);
        }
        return addressRepository.save(address);
    }

}
