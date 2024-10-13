package Proyecto.PQRSMART.Persistence.Repository;

import Proyecto.PQRSMART.Persistence.Entity.Dependence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

@org.springframework.stereotype.Repository
public interface DependenceRepository extends JpaRepository<Dependence, Long> {
    boolean existsByNameDependence(String nameDependence);
}
