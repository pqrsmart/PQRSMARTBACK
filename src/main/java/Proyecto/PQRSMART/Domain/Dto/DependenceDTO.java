package Proyecto.PQRSMART.Domain.Dto;

import Proyecto.PQRSMART.Persistence.Entity.State;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DependenceDTO {
    private Long idDependence;
    private String nameDependence;
    private State state;

}
