package school.hei.sary.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import school.hei.sary.PojaGenerated;

@PojaGenerated
@Entity
@Getter
@Setter
public class ImageModel {
    @Id private String id;
}