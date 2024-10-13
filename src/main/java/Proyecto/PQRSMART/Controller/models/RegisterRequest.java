package Proyecto.PQRSMART.Controller.models;



import Proyecto.PQRSMART.Persistence.Entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String user;
    private String password;
    private PersonType personType;
    private String name;
    private String lastName;
    private String email;
    private IdentificationType identificationType;
    private BigInteger identificationNumber;
    private BigInteger number;
    private StateUser stateUser;
    private Role role;
    private Dependence dependence;

}
