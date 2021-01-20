package eu.ecct.synthesysterminal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import eu.ecct.synthesysterminal.entity.details.Provider;
import eu.ecct.synthesysterminal.entity.details.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Account {

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
        this.password = password;
        this.pinCode = pinCode;
        this.address = address;
        this.devices = devices;
    }

}
