package eu.ecct.synthesysterminal.device;

import eu.ecct.synthesysterminal.common.controller.AccountDependentControllerOperations;
import eu.ecct.synthesysterminal.device.entity.Device;

import java.util.UUID;

interface DeviceControllerOperations extends AccountDependentControllerOperations<Device, UUID> {
}
