package Proyecto.PQRSMART.Domain.Mapper;


import Proyecto.PQRSMART.Domain.Dto.CategoryDTO;
import Proyecto.PQRSMART.Persistence.Entity.Category;

public class CategoryMapper {
    public static Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setIdCategory(categoryDTO.getIdCategory());
        category.setNameCategory(categoryDTO.getNameCategory());
        category.setDependence(categoryDTO.getDependence());
        category.setState(categoryDTO.getState());
        return category;
    }

    public static CategoryDTO toDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setIdCategory(category.getIdCategory());
        categoryDTO.setNameCategory(category.getNameCategory());
        categoryDTO.setDependence(category.getDependence());
        categoryDTO.setState(category.getState());
        return categoryDTO;
    }
}
