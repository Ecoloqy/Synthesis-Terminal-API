package eu.ecct.synthesysterminal.api;

import eu.ecct.synthesysterminal.entity.Address;
import eu.ecct.synthesysterminal.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public Iterable<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable("id") Long id) {
        return addressService.getAddressById(id);
    }

    @PostMapping
    public Address postAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

    @PutMapping
    public Address putAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

}
