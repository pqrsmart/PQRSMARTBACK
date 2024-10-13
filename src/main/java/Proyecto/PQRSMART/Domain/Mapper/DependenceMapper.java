package Proyecto.PQRSMART.Domain.Mapper;


import Proyecto.PQRSMART.Domain.Dto.DependenceDTO;
import Proyecto.PQRSMART.Persistence.Entity.Dependence;

public class DependenceMapper {
    public static Dependence toEntity(DependenceDTO dependenceDTO) {
        Dependence dependence = new Dependence();
        dependence.setIdDependence(dependenceDTO.getIdDependence());
        dependence.setNameDependence(dependenceDTO.getNameDependence());
        dependence.setState(dependenceDTO.getState());
        return dependence;
    }

    public static DependenceDTO toDTO(Dependence dependence) {
        DependenceDTO dependenceDTO = new DependenceDTO();
        dependenceDTO.setIdDependence(dependence.getIdDependence());
        dependenceDTO.setNameDependence(dependence.getNameDependence());
        dependenceDTO.setState(dependence.getState());
        return dependenceDTO;
    }
}
