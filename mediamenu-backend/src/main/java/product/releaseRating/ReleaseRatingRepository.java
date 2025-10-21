package product.releaseRating;

import org.springframework.data.jpa.repository.JpaRepository;
import product.releaseRating.model.ReleaseRating;
import product.releaseRating.model.ReleaseRatingId;

public interface ReleaseRatingRepository extends JpaRepository<ReleaseRating, ReleaseRatingId>{
}
