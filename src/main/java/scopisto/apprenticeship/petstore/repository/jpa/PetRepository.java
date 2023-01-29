package scopisto.apprenticeship.petstore.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import scopisto.apprenticeship.petstore.model.Pet;

import java.util.List;
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
