package Proyecto.PQRSMART.Domain.Service;


import Proyecto.PQRSMART.Domain.Dto.RequestDTO;
import Proyecto.PQRSMART.Domain.Mapper.RequestMapper;
import Proyecto.PQRSMART.Persistence.Entity.Request;
import Proyecto.PQRSMART.Persistence.Repository.RequestRepository;
import Proyecto.PQRSMART.Persistence.Repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestServices {
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UsuarioRepository userRepository;


    public List<RequestDTO> getAll() {
        return requestRepository.findAll().stream().map(RequestMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<RequestDTO> findById(Long id) {
        return requestRepository.findById(id).map(RequestMapper::toDTO);
    }

    public Optional<Request> findEntityById(Long id) {
        return requestRepository.findById(id);
    }

    public void update(RequestDTO requestDTO) {
        Request request = RequestMapper.toEntity(requestDTO);
        requestRepository.save(request);
    }

    public RequestDTO save(RequestDTO requestDTO) {
        Optional<Request> existingRequestOptional = requestRepository.findById(requestDTO.getIdRequest());
        if (existingRequestOptional.isPresent()) {
            Request existingRequest = existingRequestOptional.get();
            // Actualizar los campos relevantes de la solicitud existente con los valores de requestDTO
            existingRequest.setRequestState(requestDTO.getRequestState());
            existingRequest.setAnswer(requestDTO.getAnswer());
            // Actualizar otros campos si es necesario
            requestRepository.save(existingRequest);
            return requestDTO;
        } else {
            // Si la solicitud no existe, guardar una nueva solicitud en la base de datos
            requestRepository.save(RequestMapper.toEntity(requestDTO));
            return requestDTO;
        }
    }

    public RequestDTO saves(RequestDTO request) {
        // Primero, guarda la solicitud sin el radicado para que se genere el ID automáticamente.
        Request savedRequest = requestRepository.save(RequestMapper.toEntity(request));

        // Ahora que el ID ya está generado, puedes asignarlo al campo 'radicado'.
        savedRequest.setRadicado(savedRequest.getIdRequest()); // Asumiendo que getIdRequest() devuelve el ID

        // Guarda nuevamente para actualizar el radicado.
        requestRepository.save(savedRequest);

        return RequestMapper.toDTO(savedRequest);
    }
}