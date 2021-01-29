package eu.ecct.synthesysterminal.api.entity.key;

import com.sun.istack.NotNull;
import eu.ecct.synthesysterminal.api.entity.EntityOperations;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Key implements EntityOperations<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @NotNull
    private UUID value;

    @NotNull
    private boolean archived = false;

    public Key(String description, UUID value) {
        this.description = description;
        this.value = value;
    }

}
