package Proyecto.PQRSMART.Domain.Dto;

import Proyecto.PQRSMART.Persistence.Entity.Role;
import Proyecto.PQRSMART.Persistence.Entity.StateUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private  long id;

    private String name;

    private String user;

    private String lastName;

    private BigInteger identificationNumber;

    private StateUser stateUser;

    private Role role;
}
