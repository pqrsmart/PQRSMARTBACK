package Proyecto.PQRSMART.Domain.Service;

import Proyecto.PQRSMART.Controller.models.AuthResponse;
import Proyecto.PQRSMART.Controller.models.AuthenticationRequest;
import Proyecto.PQRSMART.Controller.models.RegisterRequest;
import Proyecto.PQRSMART.Persistence.Entity.User;
import org.springframework.security.core.Authentication;

public interface AuthService {
    AuthResponse register (RegisterRequest Request );
    AuthResponse registerUser (RegisterRequest Request );
    AuthResponse authenticate (AuthenticationRequest Request );
    User getCurrentUser(Authentication authentication);
}
