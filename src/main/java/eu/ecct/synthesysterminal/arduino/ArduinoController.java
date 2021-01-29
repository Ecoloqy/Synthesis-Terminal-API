package eu.ecct.synthesysterminal.arduino;

import eu.ecct.synthesysterminal.common.service.RestApiGetByUUIDServiceOperations;
import eu.ecct.synthesysterminal.common.service.RestApiServiceOperations;
import eu.ecct.synthesysterminal.device.DeviceService;
import eu.ecct.synthesysterminal.device.entity.Device;
import eu.ecct.synthesysterminal.key.entity.Key;
import eu.ecct.synthesysterminal.key.KeyService;
import eu.ecct.synthesysterminal.security.entity.DeviceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/arduino")
public class ArduinoController {

    private final DeviceService deviceService;
    private final KeyService keyService;

    @Autowired
    public ArduinoController(DeviceService deviceService, KeyService keyService) {
        this.deviceService = deviceService;
        this.keyService = keyService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Device getDeviceInfo(@ModelAttribute DeviceDetails details, @RequestHeader("Key") String deviceKey) {
        keyService.getKeyByValue(UUID.fromString(deviceKey)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.FORBIDDEN));

        return deviceService.getDeviceFromPasswords(details.getAddress(), details.getPassword()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.FORBIDDEN));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void activateDevice(@RequestBody Device device, @RequestHeader("Key") String deviceKey) {
        keyService.getKeyByValue(UUID.fromString(deviceKey)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.FORBIDDEN));

        deviceService.getElementById(device.getId()).ifPresent(dev -> {
            dev.setDeviceActivated(true);
            deviceService.updateElement(dev);
        });
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setDeviceInfo(@RequestBody Device device, @RequestHeader("Key") String deviceKey) {
        keyService.getKeyByValue(UUID.fromString(deviceKey)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.FORBIDDEN));

        deviceService.getElementById(device.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        device.setModified(false);
        deviceService.updateElement(device);
    }

}
