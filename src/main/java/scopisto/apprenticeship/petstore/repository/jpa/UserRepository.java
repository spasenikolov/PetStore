package scopisto.apprenticeship.petstore.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scopisto.apprenticeship.petstore.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
