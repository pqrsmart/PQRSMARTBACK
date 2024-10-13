package Proyecto.PQRSMART.Domain.Mapper;


import Proyecto.PQRSMART.Domain.Dto.RequestDTO;
import Proyecto.PQRSMART.Persistence.Entity.Request;

public class RequestMapper {

    public static Request toEntity(RequestDTO requestDTO) {
        Request request = new Request();
        request.setIdRequest(requestDTO.getIdRequest());
        request.setRadicado(requestDTO.getRadicado());
        request.setUser(requestDTO.getUser());
        request.setRequestType(requestDTO.getRequestType());
        request.setDependence(requestDTO.getDependence());
        request.setCategory(requestDTO.getCategory());
        request.setDescription(requestDTO.getDescription());
        request.setDate(requestDTO.getDate());
        request.setAnswer(requestDTO.getAnswer());
        request.setRequestState(requestDTO.getRequestState());
        request.setMediumAnswer(requestDTO.getMediumAnswer());
        request.setArchivo(requestDTO.getArchivo());
        return request;
    }
    public static RequestDTO toDTO(Request request) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setIdRequest(request.getIdRequest());
        requestDTO.setRadicado(request.getRadicado());
        requestDTO.setUser(request.getUser());
        requestDTO.setRequestType(request.getRequestType());
        requestDTO.setDependence(request.getDependence());
        requestDTO.setCategory(request.getCategory());
        requestDTO.setDescription(request.getDescription());
        requestDTO.setDate(request.getDate());
        requestDTO.setAnswer(request.getAnswer());
        requestDTO.setRequestState(request.getRequestState());
        requestDTO.setMediumAnswer(request.getMediumAnswer());
        requestDTO.setArchivo(request.getArchivo());
        return requestDTO;
    }
}
