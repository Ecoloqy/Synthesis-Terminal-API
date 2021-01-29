package eu.ecct.synthesysterminal.api.entity.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import eu.ecct.synthesysterminal.api.entity.EntityOperations;
import eu.ecct.synthesysterminal.api.entity.address.Address;
import eu.ecct.synthesysterminal.api.entity.device.Device;
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

    private String providerId;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @NotNull
    private boolean emailVerified = false;

    @NotNull
    private boolean archived = false;

    @NotNull
    private boolean subscription = false;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private int pinCode;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Device> devices;

    public Account(String username,
                   String email,
                   String avatarUrl,
                   String providerId,
                   Provider provider,
                   UserRole userRole,
                   boolean emailVerified,
                   boolean subscription,
                   String password,
                   int pinCode,
                   Address address,
                   List<Device> devices) {
        this.username = username;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.providerId = providerId;
        this.provider = provider;
        this.userRole = userRole;
        this.emailVerified = emailVerified;
        this.subscription = subscription;
        this.password = password;
        this.pinCode = pinCode;
        this.address = address;
        this.devices = devices;
    }

}
