package eu.ecct.server.api.device.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import eu.ecct.server.api.common.entity.EntityOperations;
import eu.ecct.server.api.pin.entity.Pin;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Device implements EntityOperations<UUID> {

    @Id
    private UUID id = UUID.randomUUID();

    @NotNull
    private String name;

    @NotNull
    @JsonIgnore
    private Long address;

    @NotNull
    @JsonIgnore
    private String password;

    @NotNull
    private boolean modified = false;

    @NotNull
    private boolean deviceActivated = false;

    @NotNull
    @JsonIgnore
    private boolean active = true;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Pin> pins;

    public Device(String name, Long address, String password, boolean modified, boolean deviceActivated, List<Pin> pins) {
        this.name = name;
        this.address = address;
        this.password = password;
        this.modified = modified;
        this.deviceActivated = deviceActivated;
        this.pins = pins;
    }

}
