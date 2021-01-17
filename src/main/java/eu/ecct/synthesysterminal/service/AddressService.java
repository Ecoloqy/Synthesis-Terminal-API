package eu.ecct.synthesysterminal.service;

import eu.ecct.synthesysterminal.entity.Address;
import eu.ecct.synthesysterminal.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Address getAddress(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public Address postAddress(Address address) {
        addressRepository.save(address);
        return address;
    }

    public Address putAddress(Address address) {
        Address oldAddress = addressRepository.getOne(address.getId());
        oldAddress.setCountry(address.getCountry());
        oldAddress.setCity(address.getCity());
        oldAddress.setStreet(address.getStreet());
        oldAddress.setZipCode(address.getZipCode());
        addressRepository.save(oldAddress);
        return oldAddress;
    }

}
