package Proyecto.PQRSMART.Domain.Dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestTypeDTO {
    private Long idRequestType;
    private String nameRequestType;
}
