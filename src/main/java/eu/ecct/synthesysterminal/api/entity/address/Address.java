package eu.ecct.synthesysterminal.api.entity.address;

import com.sun.istack.NotNull;
import eu.ecct.synthesysterminal.api.entity.EntityOperations;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Address implements EntityOperations<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Country country;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String zipCode;

    @NotNull
    private boolean archived = false;

    public Address(Country country, String city, String street, String zipCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

}
