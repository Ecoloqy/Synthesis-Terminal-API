package eu.ecct.synthesysterminal.api.controller.api.address;

import eu.ecct.synthesysterminal.api.controller.api.AbstractAccountDependentController;
import eu.ecct.synthesysterminal.api.controller.validator.RestApiControllerValidatorOperations;
import eu.ecct.synthesysterminal.api.entity.address.Address;
import eu.ecct.synthesysterminal.api.service.RestApiGetByUserOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts/{userId}/addresses")
public class AddressController extends AbstractAccountDependentController<Address, Long> implements AddressControllerOperations {

    public AddressController(RestApiGetByUserOperations<Address, Long> addressService, RestApiControllerValidatorOperations<Address, Long> validator) {
        super(addressService, validator);
    }

}
