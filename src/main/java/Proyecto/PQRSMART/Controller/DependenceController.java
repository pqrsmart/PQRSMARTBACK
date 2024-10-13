package Proyecto.PQRSMART.Controller;


import Proyecto.PQRSMART.Config.Exception.Exceptions;
import Proyecto.PQRSMART.Domain.Dto.DependenceDTO;
import Proyecto.PQRSMART.Domain.Service.DependenceService;
import Proyecto.PQRSMART.Persistence.Entity.State;
import Proyecto.PQRSMART.Persistence.Repository.DependenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dependence")
public class DependenceController {
    @Autowired
    private DependenceService dependenceService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody DependenceDTO dependenceDTO){
        try {
            DependenceDTO saveDependence = dependenceService.save(dependenceDTO);
            return ResponseEntity.ok(saveDependence);
        }catch (Exceptions.DependenciaAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La Dependencia ya existe.");
        }

    }

    @GetMapping("/get")
    public List<DependenceDTO> get(){return dependenceService.getAll();}

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody DependenceDTO dependenceDTO) {
        Optional<DependenceDTO> dependenceDTOOptional = dependenceService.findById(dependenceDTO.getIdDependence());
        if(dependenceDTOOptional.isPresent()) {
            dependenceService.save(dependenceDTO);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/cancel/{id}")
    public ResponseEntity<DependenceDTO> delete(@PathVariable Long id){
        Optional<DependenceDTO> dependenceDTOOptional = dependenceService.findById(id);
        if(dependenceDTOOptional.isPresent()) {
            DependenceDTO dependenceDTO = dependenceDTOOptional.get();
            dependenceDTO.setState(new State(2l, "DESACTIVADO"));
            dependenceService.delete(dependenceDTO);
            return ResponseEntity.ok(dependenceDTO);
        }
        return ResponseEntity.notFound().build();
    }


}
