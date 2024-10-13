package Proyecto.PQRSMART.Persistence.Repository;

import Proyecto.PQRSMART.Persistence.Entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository <State, Long>{
}
