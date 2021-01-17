package eu.ecct.synthesysterminal.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Device {

    @Id
    private UUID id = UUID.randomUUID();

    @NotNull
    private String name;

    @NotNull
    private Long connectionAddress;

    @NotNull
    private String password;

    private boolean relay1Status;
    private boolean relay2Status;
    private boolean relay3Status;
    private boolean relay4Status;
    private Double sensor1Status;
    private Double sensor2Status;
    private Double sensor3Status;
    private Double sensor4Status;

    public Device(String name, Long connectionAddress, String password) {
        this.name = name;
        this.connectionAddress = connectionAddress;
        this.password = password;
        this.relay1Status = false;
        this.relay2Status = false;
        this.relay3Status = false;
        this.relay4Status = false;
        this.sensor1Status = 0.0;
        this.sensor2Status = 0.0;
        this.sensor3Status = 0.0;
        this.sensor4Status = 0.0;
    }

}
