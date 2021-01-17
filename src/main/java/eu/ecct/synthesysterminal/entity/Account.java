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
    private String login;

    private String name;

    @NotNull
    private String password;

    private int pinCode;

    @NotNull
    @CreatedDate
    private Date registrationTime;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany
    private List<Device> devices;

}
