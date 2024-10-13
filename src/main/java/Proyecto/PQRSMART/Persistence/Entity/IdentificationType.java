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
@Table(name = "Tipos_Identificacion")
public class IdentificationType {
    @Column(name = "ID_Tipo_Identificacion")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIdentificationType;

    @Column(name = "Nombre_Tipo_Identificacion")
    private String nameIdentificationType;
}
