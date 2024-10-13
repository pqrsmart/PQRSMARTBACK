package Proyecto.PQRSMART.Persistence.Repository;

import Proyecto.PQRSMART.Persistence.Entity.RequestState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface RequestStateRepository extends JpaRepository<RequestState, Long> {
    Optional<RequestState> findByNameRequestState(String nameRequestState);
}
