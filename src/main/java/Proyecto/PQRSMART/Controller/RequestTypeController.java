package Proyecto.PQRSMART.Controller;




import Proyecto.PQRSMART.Domain.Dto.RequestTypeDTO;
import Proyecto.PQRSMART.Domain.Service.RequestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/request_type")
public class RequestTypeController {
    @Autowired
    private RequestTypeService requestTypeService;

    @PostMapping("/save")
    public RequestTypeDTO save(@RequestBody RequestTypeDTO requestTypeDTO){
        return requestTypeService.save(requestTypeDTO);
    }

    @GetMapping("/get")
    public List<RequestTypeDTO> get(){return requestTypeService.getAll();}

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody RequestTypeDTO requestTypeDTO) {
        Optional<RequestTypeDTO> requestTypeDTOOptional = requestTypeService.findById(requestTypeDTO.getIdRequestType());
        if(requestTypeDTOOptional.isPresent()) {
            requestTypeService.save(requestTypeDTO);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
