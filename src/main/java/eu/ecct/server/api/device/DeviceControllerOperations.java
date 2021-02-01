package eu.ecct.server.api.device;

import eu.ecct.server.api.common.controller.AccountDependentControllerOperations;
import eu.ecct.server.api.device.entity.Device;

import java.util.UUID;

interface DeviceControllerOperations extends AccountDependentControllerOperations<Device, UUID> {
}
