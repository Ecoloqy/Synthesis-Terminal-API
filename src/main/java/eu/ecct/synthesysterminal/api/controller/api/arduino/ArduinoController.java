package eu.ecct.synthesysterminal.api.controller.api.arduino;

import eu.ecct.synthesysterminal.api.entity.device.Device;
import eu.ecct.synthesysterminal.api.entity.key.Key;
import eu.ecct.synthesysterminal.api.service.RestApiService;
import eu.ecct.synthesysterminal.api.service.RestApiServiceOperations;
import eu.ecct.synthesysterminal.api.service.device.DeviceServiceOperations;
import eu.ecct.synthesysterminal.api.service.key.KeyService;
import eu.ecct.synthesysterminal.security.security.device.DeviceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/arduino")
public class ArduinoController {

    private final DeviceServiceOperations deviceService;
    private final KeyService keyService;

    @Autowired
    public ArduinoController(DeviceServiceOperations deviceService, KeyService keyService) {
        this.deviceService = deviceService;
        this.keyService = keyService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Device getDeviceInfo(@ModelAttribute DeviceDetails details, @RequestHeader("Key") String deviceKey) {
        Optional<Key> keyOptional = keyService.getKeyByValue(UUID.fromString(deviceKey));
        if (keyOptional.isPresent() && details.getAddress() != null && details.getPassword() != null) {
            return deviceService.getDeviceFromPasswords(details.getAddress(), details.getPassword()).orElse(null);
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void activateDevice(@RequestBody Device device, @RequestHeader("Key") String deviceKey) {
        Optional<Key> keyOptional = keyService.getKeyByValue(UUID.fromString(deviceKey));
        if (keyOptional.isPresent() && deviceService.getElementById(device.getId()).isPresent()) {
            device.setActivated(true);
            deviceService.updateElement(device);
        }

    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setDeviceInfo(@RequestBody Device device, @RequestHeader("Key") String deviceKey) {
        Optional<Key> keyOptional = keyService.getKeyByValue(UUID.fromString(deviceKey));
        if (keyOptional.isPresent() && deviceService.getElementById(device.getId()).isPresent()) {
            device.setModified(false);
            deviceService.updateElement(device);
        }

    }

}
