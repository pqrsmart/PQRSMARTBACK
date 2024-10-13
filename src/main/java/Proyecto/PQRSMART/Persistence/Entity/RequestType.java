package Proyecto.PQRSMART.Persistence.Entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tipos_Solicitud ")
public class RequestType {

    @Column(name = "ID_Tipo_Solicitud")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRequestType;

    @Column(name = "Nombre_Tipo_Solicitud")
    private String nameRequestType;
}
