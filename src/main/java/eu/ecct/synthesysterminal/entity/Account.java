package eu.ecct.synthesysterminal.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Account {

    @Id
    private UUID id = UUID.randomUUID();

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Type accountType;

    @NotNull
    private String email;

    private String name;

    @NotNull
    private String password;

    private int pinCode;

    @NotNull
    @CreatedDate
    private Date registrationTime;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Device> devices;

    public Account(String email, Type accountType, String name, String password, int pinCode, Date registrationTime, Address address, List<Device> devices) {
        this.email = email;
        this.accountType = accountType;
        this.name = name;
        this.password = password;
        this.pinCode = pinCode;
        this.registrationTime = registrationTime;
        this.address = address;
        this.devices = devices;
    }

    public enum Type {
        ADMIN, USER, DEVELOPER
    }

}
