package Proyecto.PQRSMART.Controller;

import Proyecto.PQRSMART.Config.Exception.Exceptions;
import Proyecto.PQRSMART.Controller.models.AuthResponse;
import Proyecto.PQRSMART.Controller.models.AuthenticationRequest;
import Proyecto.PQRSMART.Controller.models.RegisterRequest;
import Proyecto.PQRSMART.Domain.Dto.UsuarioDto;
import Proyecto.PQRSMART.Domain.Service.AuthService;
import Proyecto.PQRSMART.Domain.Service.JwtService;
import Proyecto.PQRSMART.Domain.Service.UsuarioService;
import Proyecto.PQRSMART.Persistence.Entity.User;
import Proyecto.PQRSMART.Persistence.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    UsuarioRepository userRepository;

    @Autowired
    private UsuarioService userService;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody RegisterRequest request){
        try {
            // Intentar registrar al usuario
            AuthResponse authResponse = authService.register(request);
            return ResponseEntity.ok(authResponse);
        } catch (Exceptions.UserAlreadyExistsException e) {
            // Manejar usuario duplicado
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe.");
        } catch (Exceptions.EmailAlreadyExistsException e) {
            // Manejar email duplicado
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo electrónico ya está en uso.");
        } catch (Exceptions.IdentificationNumberAlreadyExistsException e) {
            // Manejar número de identificación duplicado
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El número de identificación ya está registrado.");
        } catch (Exceptions.NumberAlreadyExistsException e) {
            // Manejar número de identificación duplicado
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El número ya está registrado.");
        } catch (Exception e) {
            // Manejar otros errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor.");
        }
    }


    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request){
        try {
            // Intentar registrar al usuario
            AuthResponse authResponse = authService.registerUser(request);
            return ResponseEntity.ok(authResponse);
        } catch (Exceptions.UserAlreadyExistsException e) {
            // Manejar usuario duplicado
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe.");
        } catch (Exceptions.EmailAlreadyExistsException e) {
            // Manejar email duplicado
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo electrónico ya está en uso.");
        } catch (Exceptions.NumberAlreadyExistsException e) {
            // Manejar número de identificación duplicado
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El número ya está registrado.");
        } catch (Exceptions.IdentificationNumberAlreadyExistsException e) {
            // Manejar número de identificación duplicado
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El número de identificación ya está registrado.");
        } catch (Exception e) {
            // Manejar otros errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor.");
        }

    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            // Intentar autenticar al usuario usando el servicio
            AuthResponse authResponse = authService.authenticate(request);

            // Obtener el usuario desde el repositorio
            User user = userRepository.findByUser(request.getUser());

            // Verificar el estado del usuario
            if (user.getStateUser().getId() == 3) {
                // Usuario bloqueado (DESACTIVADO)
                return ResponseEntity.status(HttpStatus.LOCKED).body("La cuenta está bloqueada.");
            } else if (user.getStateUser().getId() == 1) {
                // Usuario inactivo (INACTIVO)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La cuenta está inactiva.");
            }

            // Si todo está bien, retornar la respuesta de autenticación
            return ResponseEntity.ok(authResponse);

        } catch (BadCredentialsException e) {
            // Manejar credenciales incorrectas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales incorrectas. Verifique su usuario y contraseña.");

        } catch (LockedException e) {
            // Manejar cuenta bloqueada (DESACTIVADA)
            return ResponseEntity.status(HttpStatus.LOCKED).body("La cuenta está bloqueada.");

        } catch (DisabledException e) {
            // Manejar cuenta inactiva
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La cuenta está inactiva.");

        } catch (AuthenticationException e) {
            // Manejar errores generales de autenticación
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("No se pudo autenticar. Por favor, verifique los datos.");

        } catch (Exception e) {
            // Manejar errores generales (problemas en el servidor, etc.)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en el servidor. Por favor, intente más tarde.");
        }
    }


    @GetMapping("/editar")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        return ResponseEntity.ok(authService.getCurrentUser(authentication));
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activateUser(@RequestBody UsuarioDto userDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        userDTO.setUser(username);  // Assuming the UserDTO has a user field to store the username
        UsuarioDto updatedUser = userService.upda(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
        try {
            if (jwtService.validateToken(token)) {
                String username = jwtService.getUserName(token);
                userService.verifyUser(username); // Implementa la lógica para marcar al usuario como verificado
                return ResponseEntity.ok("Correo electrónico verificado correctamente.");
            } else {
                return ResponseEntity.badRequest().body("Enlace de verificación inválido o expirado.");
            }
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.internalServerError().body("error"+e);
        }

    }

}
