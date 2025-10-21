package product.trackRating;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.trackRating.model.TrackRating;
import product.trackRating.model.TrackRatingId;

@Repository
public interface TrackRatingRepository extends JpaRepository<TrackRating, TrackRatingId> {
}
