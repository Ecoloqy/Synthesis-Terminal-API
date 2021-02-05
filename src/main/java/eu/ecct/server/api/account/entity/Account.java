package eu.ecct.server.api.account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import eu.ecct.server.api.address.entity.Address;
import eu.ecct.server.api.common.entity.EntityOperations;
import eu.ecct.server.api.device.entity.Device;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Account implements EntityOperations<UUID> {

    @Id
    private UUID id = UUID.randomUUID();

    @NotNull
    private String username;
    
    private String email;

    @NotNull
    private String avatarUrl = "img/guest_avatar.png";

    private Long providerId;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @NotNull
    @JsonIgnore
    private boolean active = true;

    @NotNull
    private boolean subscription = false;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Device> devices;

    public Account(String username,
                   String email,
                   String avatarUrl,
                   Long providerId,
                   Provider provider,
                   UserRole userRole,
                   boolean subscription,
                   Address address,
                   List<Device> devices) {
        this.username = username;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.providerId = providerId;
        this.provider = provider;
        this.userRole = userRole;
        this.subscription = subscription;
        this.address = address;
        this.devices = devices;
    }

}
