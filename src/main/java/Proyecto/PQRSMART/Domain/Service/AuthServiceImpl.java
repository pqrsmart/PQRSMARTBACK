package Proyecto.PQRSMART.Domain.Service;

import Proyecto.PQRSMART.Config.Exception.Exceptions;
import Proyecto.PQRSMART.Controller.models.AuthResponse;
import Proyecto.PQRSMART.Controller.models.AuthenticationRequest;
import Proyecto.PQRSMART.Controller.models.RegisterRequest;
import Proyecto.PQRSMART.Persistence.Entity.Role;
import Proyecto.PQRSMART.Persistence.Entity.StateUser;
import Proyecto.PQRSMART.Persistence.Entity.User;
import Proyecto.PQRSMART.Persistence.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UsuarioRepository userRepository;
    private final EmailServiceImpl emailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUser(request.getUser())) {
            throw new Exceptions.UserAlreadyExistsException("El usuario ya existe.");
        }

        // Verificar si el email ya existe
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new Exceptions.EmailAlreadyExistsException("El correo electrónico ya está en uso.");
        }

        // Verificar si el número de identificación ya existe
        if (userRepository.existsByIdentificationNumber(request.getIdentificationNumber())) {
            throw new Exceptions.IdentificationNumberAlreadyExistsException("El número de identificación ya está registrado.");
        }
        // Verificar si el número de numero ya existe
        if (userRepository.existsByNumber(request.getNumber())) {
            throw new Exceptions.NumberAlreadyExistsException("El número ya está registrado.");
        }

        var user = User.builder()
                .user(request.getUser())
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .stateUser(new StateUser(1L, "INACTIVO"))
                .identificationType(request.getIdentificationType())
                .identificationNumber(request.getIdentificationNumber())
                .personType(request.getPersonType())
                .number(request.getNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .dependence(request.getDependence())
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.genereteToken((UserDetails) user);
        // Enviar correo electrónico de activación
        String activationLink1 = "https://pqrsmart.netlify.app/activate/"+jwtToken;
        String mensajeHtml = String.format(
                "<h1>Hola %s %s</h1>" +
                        "<p>Gracias por iniciar el proceso de verificación de identidad en nuestra plataforma. Para completar la verificación, por favor haz clic en el siguiente enlace:" +
                        "<br /><br />" +
                        "<a href=\"%s\">Verificar Identidad</a>" +
                        "<br /><br />" +
                        "Este enlace te llevará a una página donde podrás confirmar tu identidad. Una vez completado este paso, tu verificación estará finalizada y podrás acceder a todos los beneficios de nuestra plataforma de manera segura." +
                        "<br /><br />" +
                        "Si tienes alguna pregunta o necesitas asistencia durante este proceso, no dudes en contactarnos respondiendo a este correo." +
                        "<br /><br />" +
                        "Gracias de nuevo por tu colaboración." +
                        "<br /><br />" +
                        "<br /><br />" +
                        "<br /><br />" +
                        "PQRSmart<br /><br />" ,
                user.getName(), user.getLastName(), activationLink1
        );

        emailService.sendEmails(
                new String[]{user.getEmail()},
                "Confirma tu correo",
                mensajeHtml
        );
        return AuthResponse.builder()
                .token(jwtToken).build();
    }

    @Override
    public AuthResponse registerUser(RegisterRequest request) {
        if (userRepository.existsByUser(request.getUser())) {
            throw new Exceptions.UserAlreadyExistsException("El usuario ya existe.");
        }

        // Verificar si el email ya existe
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new Exceptions.EmailAlreadyExistsException("El correo electrónico ya está en uso.");
        }

        // Verificar si el número de identificación ya existe
        if (userRepository.existsByIdentificationNumber(request.getIdentificationNumber())) {
            throw new Exceptions.IdentificationNumberAlreadyExistsException("El número de identificación ya está registrado.");
        }
        // Verificar si el número de numero ya existe
        if (userRepository.existsByNumber(request.getNumber())) {
            throw new Exceptions.NumberAlreadyExistsException("El número ya está registrado.");
        }
        var user = User.builder()
                .user(request.getUser())
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .stateUser(new StateUser(1L, "INACTIVO"))
                .identificationType(request.getIdentificationType())
                .identificationNumber(request.getIdentificationNumber())
                .personType(request.getPersonType())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .number(request.getNumber())
                .dependence(request.getDependence())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.genereteToken((UserDetails) user);
        // Enviar correo electrónico de activación
        String activationLink1 = "https://pqrsmart.netlify.app/activate/"+jwtToken;
        String mensajeHtml = String.format(
                "<h1>Hola %s %s</h1>" +
                        "<p>Gracias por iniciar el proceso de verificación de identidad en nuestra plataforma. Para completar la verificación, por favor haz clic en el siguiente enlace:" +
                        "<br /><br />" +
                        "<a href=\"%s\">Verificar Identidad</a>" +
                        "<br /><br />" +
                        "Este enlace te llevará a una página donde podrás confirmar tu identidad. Una vez completado este paso, tu verificación estará finalizada y podrás acceder a todos los beneficios de nuestra plataforma de manera segura." +
                        "<br /><br />" +
                        "Si tienes alguna pregunta o necesitas asistencia durante este proceso, no dudes en contactarnos respondiendo a este correo." +
                        "<br /><br />" +
                        "Gracias de nuevo por tu colaboración." +
                        "<br /><br />" +
                        "<br /><br />" +
                        "M<br /><br />" +
                        "PQRSmart<br /><br />",
                user.getName(), user.getLastName(), activationLink1
        );

        emailService.sendEmails(
                new String[]{user.getEmail()},
                "Confirma tu correo",
                mensajeHtml
        );
        return AuthResponse.builder()
                .token(jwtToken).build();
    }

    @Override
    public AuthResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUser(),
                        request.getPassword()
                )
        );
        UserDetails user = userRepository.findUserByUser(request.getUser()).orElseThrow();
        String jwtToken = jwtService.genereteToken(user);
        List<String> roles = user.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());

        return AuthResponse.builder()
                .token(jwtToken)
                .authorities(roles)
                .build();
    }

    @Override
    public User getCurrentUser(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }



}

