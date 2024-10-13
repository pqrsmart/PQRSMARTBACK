package Proyecto.PQRSMART.Persistence.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Solicitudes ")
public class Request {

    @Column(name = "ID_Solicitud")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRequest;

    @Column(name = "Radicado")
    private Long radicado;

    @JoinColumn(name = "Usuario")
    @ManyToOne
    private User user;

    @JoinColumn(name = "ID_Tipo_Solicitud")
    @ManyToOne(fetch = FetchType.EAGER)
    private RequestType requestType;

    @JoinColumn(name = "ID_Categoria")
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @Column(name = "Descripcion_Solicitud", length = 1000)
    private String description;

    @Column(name = "Fecha_Solicitud")
    private LocalDate date;

    @Column(name = "Respuesta_Solicitud")
    private String answer;

    @JoinColumn(name = "Id_Estado_Solicitud")
    @ManyToOne(fetch = FetchType.EAGER)
    private RequestState requestState;

    @Column(name = "Medio_Respuesta")
    private String mediumAnswer;

    @JoinColumn (name = "ID_Dependencia")
    @ManyToOne
    private Dependence dependence;

    @Lob
    @Column(name = "Archivo")
    private String archivo;

    @Lob
    @Column(name = "Archivo_Respuesta")
    private String archivoAnswer;

    // Método para asignar el radicado antes de persistir
    @PrePersist
    public void asignarRadicado() {
        // Asigna el idRequest al radicado si es nulo o si necesitas que se incremente
        if (this.radicado == null) {
            this.radicado = this.idRequest;
        }
    }

}
