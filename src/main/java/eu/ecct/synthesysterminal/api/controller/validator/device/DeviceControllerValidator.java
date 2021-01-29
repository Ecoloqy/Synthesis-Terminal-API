package eu.ecct.synthesysterminal.api.controller.validator.device;

import eu.ecct.synthesysterminal.api.controller.validator.RestApiControllerValidator;
import eu.ecct.synthesysterminal.api.entity.account.Account;
import eu.ecct.synthesysterminal.api.entity.device.Device;
import eu.ecct.synthesysterminal.api.service.RestApiGetByUserOperations;
import eu.ecct.synthesysterminal.api.service.RestApiServiceOperations;
import eu.ecct.synthesysterminal.api.service.device.DeviceServiceOperations;
import eu.ecct.synthesysterminal.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class DeviceControllerValidator extends RestApiControllerValidator<Device, UUID> implements DeviceControllerValidatorOperations {

    @Autowired
    public DeviceControllerValidator(RestApiGetByUserOperations<Device, UUID> service,
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
