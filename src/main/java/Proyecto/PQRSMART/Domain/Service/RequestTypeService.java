package Proyecto.PQRSMART.Domain.Service;


import Proyecto.PQRSMART.Domain.Dto.RequestTypeDTO;
import Proyecto.PQRSMART.Domain.Mapper.RequestTypeMapper;
import Proyecto.PQRSMART.Persistence.Repository.RequestTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestTypeService {
    @Autowired
    private RequestTypeRepository requestTypeRepository;

    public RequestTypeDTO save(RequestTypeDTO requestTypeDTO) {
        requestTypeRepository.save(RequestTypeMapper.toEntinty(requestTypeDTO));
        return requestTypeDTO;
    }

    public List<RequestTypeDTO> getAll() {
        return requestTypeRepository.findAll().stream().map(RequestTypeMapper::toDto).collect(Collectors.toList());
    }

    public Optional<RequestTypeDTO> findById(Long id) {
        return requestTypeRepository.findById(id).map(RequestTypeMapper::toDto);
    }
}
