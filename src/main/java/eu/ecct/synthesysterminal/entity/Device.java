package eu.ecct.synthesysterminal.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
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
    private Long address;

    @NotNull
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Pin> pins;

    public Device(String name, Long address, String password, List<Pin> pins) {
        this.name = name;
        this.address = address;
        this.password = password;
        this.pins = pins;
    }

}
