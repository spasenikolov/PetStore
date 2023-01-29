package scopisto.apprenticeship.petstore.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scopisto.apprenticeship.petstore.model.HistoryLog;

@Repository
public interface HistoryLogRepository extends JpaRepository<HistoryLog, Long> {
}
