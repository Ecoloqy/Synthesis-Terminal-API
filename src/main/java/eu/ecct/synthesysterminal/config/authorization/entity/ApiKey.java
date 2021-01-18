package eu.ecct.synthesysterminal.config.authorization.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String key;

    @NotNull
    private String resource;

    public ApiKey(String key, String resource) {
        this.key = key;
        this.resource = resource;
    }

}
