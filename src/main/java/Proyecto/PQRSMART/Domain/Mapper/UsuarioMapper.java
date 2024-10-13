package Proyecto.PQRSMART.Domain.Mapper;


import Proyecto.PQRSMART.Domain.Dto.UsuarioDto;
import Proyecto.PQRSMART.Persistence.Entity.User;

public class UsuarioMapper {

    public static User toEntity(UsuarioDto userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setIdentificationNumber(userDTO.getIdentificationNumber());
        user.setStateUser(userDTO.getStateUser());
        user.setUser(userDTO.getUser());
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setRole(userDTO.getRole());
        return user;
    }

    public static UsuarioDto toDto(User user) {
        UsuarioDto userDTO = new UsuarioDto();
        userDTO.setId(user.getId());
        userDTO.setIdentificationNumber(user.getIdentificationNumber());
        userDTO.setStateUser(user.getStateUser());
        userDTO.setUser(user.getUser());
        userDTO.setName(user.getName());
        userDTO.setLastName(user.getLastName());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
