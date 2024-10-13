package Proyecto.PQRSMART.Persistence.Repository;

import Proyecto.PQRSMART.Persistence.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameCategory(String nameCategory);

}
