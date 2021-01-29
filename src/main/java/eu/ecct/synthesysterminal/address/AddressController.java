package eu.ecct.synthesysterminal.address;

import eu.ecct.synthesysterminal.common.controller.AbstractAccountDependentController;
import eu.ecct.synthesysterminal.common.controller.validator.RestApiControllerValidatorOperations;
import eu.ecct.synthesysterminal.address.entity.Address;
import eu.ecct.synthesysterminal.common.service.RestApiGetByUUIDServiceOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts/{userId}/addresses")
public class AddressController extends AbstractAccountDependentController<Address, Long> implements AddressControllerOperations {

    public AddressController(RestApiGetByUUIDServiceOperations<Address, Long> addressService, RestApiControllerValidatorOperations<Address, Long> validator) {
        super(addressService, validator);
    }

}
