package eu.ecct.synthesysterminal.device;

import eu.ecct.synthesysterminal.common.controller.validator.RestApiControllerValidator;
import eu.ecct.synthesysterminal.account.entity.Account;
import eu.ecct.synthesysterminal.device.entity.Device;
import eu.ecct.synthesysterminal.common.service.RestApiGetByUUIDServiceOperations;
import eu.ecct.synthesysterminal.common.service.RestApiServiceOperations;
import eu.ecct.synthesysterminal.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
class DeviceControllerValidator extends RestApiControllerValidator<Device, UUID> implements DeviceControllerValidatorOperations {

    @Autowired
    public DeviceControllerValidator(RestApiGetByUUIDServiceOperations<Device, UUID> service,
                                     RestApiServiceOperations<Account, UUID> accountService,
                                     AccountDetailsService accountDetailsService) {
        super(service, accountService, accountDetailsService);
    }

    @Override
    public boolean isOwnerManipulatingWithElement(UUID id) {
        Optional<Account> optionalActualAccount = accountDetailsService.getActiveAccount();
        if (optionalActualAccount.isPresent() && optionalActualAccount.get().getDevices() != null) {
            for (Device device : optionalActualAccount.get().getDevices()) {
                if (device.getId().equals(id)) return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAddingAble(Device device) {
        DeviceServiceOperations deviceService = (DeviceServiceOperations) service;
        return deviceService.getDeviceFromPasswords(device.getAddress(), device.getPassword()).isEmpty();
    }

}
