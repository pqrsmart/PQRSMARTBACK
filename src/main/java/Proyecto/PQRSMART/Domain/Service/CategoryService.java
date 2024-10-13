package Proyecto.PQRSMART.Domain.Service;

import Proyecto.PQRSMART.Config.Exception.Exceptions;
import Proyecto.PQRSMART.Domain.Dto.CategoryDTO;
import Proyecto.PQRSMART.Domain.Mapper.CategoryMapper;
import Proyecto.PQRSMART.Persistence.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
@Autowired
    private CategoryRepository categoryRepository;


    public CategoryDTO save(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByNameCategory(categoryDTO.getNameCategory())) {
            throw new Exceptions.CategoryAlreadyExistsException("La Categoria ya existe.");
        }
        categoryRepository.save(CategoryMapper.toEntity(categoryDTO));
        return categoryDTO;
    }

    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll().stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<CategoryDTO> findById(Long id) {
        return categoryRepository.findById(id).map(CategoryMapper::toDTO);
    }
    public CategoryDTO delete(CategoryDTO categoryDTO) {
        categoryRepository.save(CategoryMapper.toEntity(categoryDTO));
        return categoryDTO;
    }
}
