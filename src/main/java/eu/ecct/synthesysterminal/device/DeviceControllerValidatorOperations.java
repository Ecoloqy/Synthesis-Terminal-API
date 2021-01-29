package eu.ecct.synthesysterminal.device;

import eu.ecct.synthesysterminal.common.controller.validator.RestApiControllerValidatorOperations;
import eu.ecct.synthesysterminal.device.entity.Device;

import java.util.UUID;

interface DeviceControllerValidatorOperations extends RestApiControllerValidatorOperations<Device, UUID> {
}
