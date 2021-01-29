package eu.ecct.synthesysterminal.device;

import eu.ecct.synthesysterminal.common.controller.AbstractAccountDependentController;
import eu.ecct.synthesysterminal.common.controller.validator.RestApiControllerValidatorOperations;
import eu.ecct.synthesysterminal.device.entity.Device;
import eu.ecct.synthesysterminal.common.service.RestApiGetByUUIDServiceOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts/{userId}/devices")
public class DeviceController extends AbstractAccountDependentController<Device, UUID> implements DeviceControllerOperations {

    public DeviceController(RestApiGetByUUIDServiceOperations<Device, UUID> addressService, RestApiControllerValidatorOperations<Device, UUID> validator) {
        super(addressService, validator);
    }

}
