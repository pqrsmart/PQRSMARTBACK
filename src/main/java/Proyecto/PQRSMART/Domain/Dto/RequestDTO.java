package Proyecto.PQRSMART.Domain.Dto;

import Proyecto.PQRSMART.Persistence.Entity.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDTO {
    private Long idRequest;
    private User user;
    private RequestType requestType;
    private Dependence dependence;
    private Category category;
    private String description;
    private LocalDate date;
    private String answer;
    private RequestState requestState;
    private String mediumAnswer;
    private String archivo;
    private Long radicado = idRequest;

}
