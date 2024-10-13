package Proyecto.PQRSMART.Persistence.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Dependencias")
public class Dependence {

    @Column(name = "ID_Dependencia")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDependence;

    @Column(name = "Nombre_Dependencia", unique = true)
    private String nameDependence;

    @ManyToOne
    @JoinColumn(name = "Estado")
    private State state;
}
