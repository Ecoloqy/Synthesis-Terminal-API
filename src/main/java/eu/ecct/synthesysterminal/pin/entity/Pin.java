package eu.ecct.synthesysterminal.pin.entity;

import com.sun.istack.NotNull;
import eu.ecct.synthesysterminal.common.entity.EntityOperations;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Pin implements EntityOperations<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int pinId;
    private String switchName;
    private boolean switchStatus;
    private double sensorValue;

    @Enumerated(EnumType.STRING)
    private PinType pinType;

    @NotNull
    private boolean active = true;

    public Pin(int pinId, String switchName, boolean switchStatus, double sensorValue, PinType pinType) {
        this.pinId = pinId;
        this.switchName = switchName;
        this.switchStatus = switchStatus;
        this.sensorValue = sensorValue;
        this.pinType = pinType;
    }

}
