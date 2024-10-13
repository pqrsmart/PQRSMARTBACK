package Proyecto.PQRSMART.Controller;

import Proyecto.PQRSMART.Config.Exception.Exceptions;
import Proyecto.PQRSMART.Controller.models.AuthResponse;
import Proyecto.PQRSMART.Domain.Dto.CategoryDTO;
import Proyecto.PQRSMART.Domain.Service.CategoryService;
import Proyecto.PQRSMART.Persistence.Entity.State;
import Proyecto.PQRSMART.Persistence.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CategoryDTO categoryDTO) {
        try {
            CategoryDTO savedCategory = categoryService.save(categoryDTO);
            return ResponseEntity.ok(savedCategory);
        } catch (Exceptions.CategoryAlreadyExistsException e) {
            // Manejar usuario duplicado
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La Categoria ya existe.");
        }
    }


    @GetMapping("/get")
    public List<CategoryDTO> get(){return categoryService.getAll();}

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CategoryDTO categoryDTO) {
        Optional<CategoryDTO> categoryDTOOptional = categoryService.findById(categoryDTO.getIdCategory());
        if(categoryDTOOptional.isPresent()) {
            categoryService.save(categoryDTO);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PatchMapping("/cancel/{id}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long id){
        Optional<CategoryDTO> categoryDTOOptional = categoryService.findById(id);
        if(categoryDTOOptional.isPresent()) {
            CategoryDTO categoryDTO = categoryDTOOptional.get();
            categoryDTO.setState(new State(2l, "DESACTIVADO"));
            categoryService.delete(categoryDTO);
            return ResponseEntity.ok(categoryDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
