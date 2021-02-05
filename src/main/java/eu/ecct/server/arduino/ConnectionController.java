package eu.ecct.server.arduino;

import eu.ecct.server.api.device.DeviceService;
import eu.ecct.server.api.device.entity.Device;
import eu.ecct.server.arduino.entity.ArduinoCredentials;
import eu.ecct.server.arduino.key.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/connection")
public class ConnectionController {

    private final DeviceService deviceService;
    private final KeyService keyService;

    @Autowired
    public ConnectionController(DeviceService deviceService, KeyService keyService) {
        this.deviceService = deviceService;
        this.keyService = keyService;
    }

    @GetMapping
    public Device getDeviceById(@ModelAttribute ArduinoCredentials credentials, @RequestHeader("Key") UUID keyValue) {
        keyService.findByValue(keyValue).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return deviceService.getDeviceFromPasswords(credentials.getAddress(), credentials.getPassword()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public void updateDeviceById(@RequestBody Device device, @PathVariable UUID id, @RequestHeader("Key") UUID keyValue) {
        keyService.findByValue(keyValue).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        if (!device.getId().equals(id)) throw new ResponseStatusException(HttpStatus.CONFLICT);
        deviceService.patchElement(device, id);
    }

}
