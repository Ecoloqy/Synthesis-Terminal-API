package eu.ecct.synthesysterminal.api.service.pin;

import eu.ecct.synthesysterminal.api.entity.pin.Pin;
import eu.ecct.synthesysterminal.api.repository.RestApiRepository;
import eu.ecct.synthesysterminal.api.service.AbstractRestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PinService extends AbstractRestApiService<Pin, Long> implements PinServiceOperations {

    @Autowired
    public PinService(RestApiRepository<Pin, Long> repository) {
        super(repository);
    }

    @Override
    public Iterable<Pin> getAllElements() {
        return repository.getAllByArchived(false);
    }

    @Override
    public Optional<Pin> getElementById(Long id) {
        return repository.getByIdAndArchived(id, false);
    }

    @Override
    public boolean updateElement(Pin pin) {
        Optional<Pin> oldPinOptional = repository.findById(pin.getId());
        if (oldPinOptional.isPresent()) {
            Pin oldPin = oldPinOptional.get();
            oldPin.setPinId(pin.getPinId());
            oldPin.setPinType(pin.getPinType());
            oldPin.setSensorValue(pin.getSensorValue());
            oldPin.setSwitchName(pin.getSwitchName());
            oldPin.setSwitchStatus(pin.isSwitchStatus());
            oldPin.setArchived(pin.isArchived());
            repository.save(oldPin);
            return true;
        }
        return false;
    }

    @Override
    public boolean patchElement(Pin pin, Long id) {
        Optional<Pin> oldPinOptional = repository.findById(id);
        if (oldPinOptional.isPresent()) {
            Pin oldPin = oldPinOptional.get();
            if (pin.getPinType() != null) oldPin.setPinType(pin.getPinType());
            if (pin.getSwitchName() != null) oldPin.setSwitchName(pin.getSwitchName());
            oldPin.setSensorValue(pin.getSensorValue());
            oldPin.setSwitchStatus(pin.isSwitchStatus());
            repository.save(oldPin);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteElement(Long id) {
        Optional<Pin> oldPinOptional = repository.findById(id);
        if (oldPinOptional.isPresent()) {
            Pin oldPin = oldPinOptional.get();
            oldPin.setArchived(true);
            repository.save(oldPin);
            return true;
        }
        return false;
    }

}
