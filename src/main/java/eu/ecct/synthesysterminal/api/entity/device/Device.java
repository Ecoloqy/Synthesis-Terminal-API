package eu.ecct.synthesysterminal.api.entity.device;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import eu.ecct.synthesysterminal.api.entity.EntityOperations;
import eu.ecct.synthesysterminal.api.entity.pin.Pin;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private boolean activated = false;

    @NotNull
    private boolean archived = false;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Pin> pins;

    public Device(String name, Long address, String password, List<Pin> pins) {
        this.name = name;
        this.address = address;
        this.password = password;
        this.pins = pins;
    }

}
