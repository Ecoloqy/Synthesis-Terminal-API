package eu.ecct.server.api.device;

import eu.ecct.server.api.common.controller.AbstractAccountDependentController;
import eu.ecct.server.api.common.service.RestApiGetByUUIDServiceOperations;
import eu.ecct.server.api.device.entity.Device;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts/{userId}/devices")
public class DeviceController extends AbstractAccountDependentController<Device, UUID> implements DeviceControllerOperations {

    public DeviceController(RestApiGetByUUIDServiceOperations<Device, UUID> service) {
        super(service);
    }

}
