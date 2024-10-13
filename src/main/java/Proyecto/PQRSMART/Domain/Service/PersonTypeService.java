package Proyecto.PQRSMART.Domain.Service;

import Proyecto.PQRSMART.Domain.Dto.PersonTypeDTO;
import Proyecto.PQRSMART.Domain.Mapper.PersonTypeMapper;
import Proyecto.PQRSMART.Persistence.Repository.PersonTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonTypeService {
@Autowired
    private PersonTypeRepository personTypeRepository;


    public PersonTypeDTO save(PersonTypeDTO personTypeDTO) {
        personTypeRepository.save(PersonTypeMapper.toEntinty(personTypeDTO));
        return personTypeDTO;
    }

    public List<PersonTypeDTO> getAll() {
        return personTypeRepository.findAll().stream().map(PersonTypeMapper::toDto).collect(Collectors.toList());
    }
    public Optional<PersonTypeDTO> findById(Long id) {
        return personTypeRepository.findById(id).map(PersonTypeMapper::toDto);
    }
}
