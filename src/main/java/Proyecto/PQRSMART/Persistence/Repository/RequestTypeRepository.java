package Proyecto.PQRSMART.Persistence.Repository;




import Proyecto.PQRSMART.Persistence.Entity.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface RequestTypeRepository extends JpaRepository<RequestType, Long> {
}
