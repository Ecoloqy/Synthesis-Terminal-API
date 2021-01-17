package eu.ecct.synthesysterminal.entity;

import com.sun.istack.NotNull;
import eu.ecct.synthesysterminal.entity.models.Country;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Address {

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

    public Address(Country country, String city, String street, String zipCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

}
