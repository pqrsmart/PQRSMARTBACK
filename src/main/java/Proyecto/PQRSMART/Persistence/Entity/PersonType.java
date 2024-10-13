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
@Table(name = "Tipos_Persona")
public class PersonType {

    @Column(name="Id_Tipo_Persona")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersonType;


    @Column(name = "Nombre_Tipo_Persona")
    private String namePersonType;
}
