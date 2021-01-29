package eu.ecct.synthesysterminal.api.controller.api.device;

import eu.ecct.synthesysterminal.api.controller.api.AbstractAccountDependentController;
import eu.ecct.synthesysterminal.api.controller.validator.RestApiControllerValidatorOperations;
import eu.ecct.synthesysterminal.api.entity.device.Device;
import eu.ecct.synthesysterminal.api.service.RestApiGetByUserOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts/{userId}/devices")
public class DeviceController extends AbstractAccountDependentController<Device, UUID> implements DeviceControllerOperations {

    public DeviceController(RestApiGetByUserOperations<Device, UUID> addressService, RestApiControllerValidatorOperations<Device, UUID> validator) {
        super(addressService, validator);
    }

}
