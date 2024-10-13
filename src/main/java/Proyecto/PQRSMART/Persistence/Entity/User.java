package Proyecto.PQRSMART.Persistence.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Usuario")
public class User implements UserDetails {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column(name = "Nombre")
    private String name;

    @Column(name = "Usuario", unique = true)
    private String user;

    @Column(name = "Apellido_Persona")
    private String lastName;

    @Column(name = "Correo_Persona", unique = true)
    private String email;

    @Column(name = "Contraseña")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "Roles")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "Estado_Usuario")
    private StateUser stateUser;

    @JoinColumn(name = "ID_Tipo_Identificacion")
    @ManyToOne
    private IdentificationType identificationType;

    @Column(name = "Numero_Identificacion_Persona", unique = true)
    private BigInteger identificationNumber;

    @Column(name = "Numero", unique = true)
    private BigInteger number;

    @JoinColumn(name = "ID_Tipo_Persona")
    @ManyToOne
    private PersonType personType;


    @ManyToOne @JoinColumn(name = "ID_Dependencia")
    private Dependence dependence;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
