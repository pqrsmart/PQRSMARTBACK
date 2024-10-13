package Proyecto.PQRSMART.Controller;


import Proyecto.PQRSMART.Domain.Dto.RequestStateDTO;
import Proyecto.PQRSMART.Domain.Service.RequestStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/request_state")
public class RequestStateController {
    @Autowired
    private RequestStateService requestStateService;

    @PostMapping("/save")
    public RequestStateDTO save(@RequestBody RequestStateDTO requestStateDTO){
        return requestStateService.save(requestStateDTO);
    }

    @GetMapping("/get")
    public List<RequestStateDTO> get(){return requestStateService.getAll();}

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody RequestStateDTO requestStateDTO) {
        Optional<RequestStateDTO> requestStateDTOOptional = requestStateService.findById(requestStateDTO.getIdRequestState());
        if(requestStateDTOOptional.isPresent()) {
            requestStateService.save(requestStateDTO);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
