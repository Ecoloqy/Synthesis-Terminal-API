package eu.ecct.server.api.address;

import eu.ecct.server.api.address.entity.Address;
import eu.ecct.server.api.common.controller.AbstractAccountDependentController;
import eu.ecct.server.api.common.service.RestApiGetByUUIDServiceOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts/{userId}/addresses")
public class AddressController extends AbstractAccountDependentController<Address, Long> implements AddressControllerOperations {

    public AddressController(RestApiGetByUUIDServiceOperations<Address, Long> addressService) {
        super(addressService);
    }

}
