package product.track;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.track.model.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {
}
