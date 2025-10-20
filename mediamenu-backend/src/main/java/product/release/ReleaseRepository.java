package product.release;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.release.model.Release;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Integer> {
}
