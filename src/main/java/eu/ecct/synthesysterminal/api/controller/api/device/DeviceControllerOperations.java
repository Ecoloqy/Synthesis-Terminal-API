package eu.ecct.synthesysterminal.api.controller.api.device;

import eu.ecct.synthesysterminal.api.controller.api.AccountDependentControllerOperations;
import eu.ecct.synthesysterminal.api.entity.device.Device;

import java.util.UUID;

public interface DeviceControllerOperations extends AccountDependentControllerOperations<Device, UUID> {
}
