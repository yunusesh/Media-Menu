package product.scrobble;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.scrobble.model.Scrobble;

@Repository
public interface ScrobbleRepository extends JpaRepository<Scrobble, Integer> {
}
