package Proyecto.PQRSMART.Domain.Service;

import Proyecto.PQRSMART.Config.Exception.Exceptions;
import Proyecto.PQRSMART.Domain.Dto.DependenceDTO;
import Proyecto.PQRSMART.Domain.Mapper.DependenceMapper;
import Proyecto.PQRSMART.Persistence.Repository.DependenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DependenceService {
@Autowired
    private DependenceRepository dependenceRepository;

    public DependenceDTO save(DependenceDTO dependenceDTO) {
        if (dependenceRepository.existsByNameDependence(dependenceDTO.getNameDependence())) {
            throw new Exceptions.CategoryAlreadyExistsException("La Dependencia ya existe.");
        }
        dependenceRepository.save(DependenceMapper.toEntity(dependenceDTO));
        return dependenceDTO;
    }
    public DependenceDTO delete(DependenceDTO dependenceDTO) {
        dependenceRepository.save(DependenceMapper.toEntity(dependenceDTO));
        return dependenceDTO;
    }
    public DependenceDTO activate(DependenceDTO dependenceDTO) {
        dependenceRepository.save(DependenceMapper.toEntity(dependenceDTO));
        return dependenceDTO;
    }

    public List<DependenceDTO> getAll() {
        return dependenceRepository.findAll().stream().map(DependenceMapper::toDTO).collect(Collectors.toList());
    }
    public Optional<DependenceDTO> findById(Long id) {
        return dependenceRepository.findById(id).map(DependenceMapper::toDTO);
    }
}
