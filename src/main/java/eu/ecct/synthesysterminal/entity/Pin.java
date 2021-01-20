package eu.ecct.synthesysterminal.entity;

import eu.ecct.synthesysterminal.entity.details.PinType;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Pin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean switchStatus;
    private Double sensorValue;

    @Enumerated(EnumType.STRING)
    private PinType pinType;

    public Pin(boolean switchStatus, Double sensorValue, PinType pinType) {
        this.switchStatus = switchStatus;
        this.sensorValue = sensorValue;
        this.pinType = pinType;
    }

}
