package product.releaseRating;

import org.springframework.data.jpa.repository.JpaRepository;
import product.releaseRating.model.ReleaseRating;
import product.releaseRating.model.ReleaseRatingId;

import java.util.List;
import java.util.Optional;

public interface ReleaseRatingRepository extends JpaRepository<ReleaseRating, ReleaseRatingId>{
    List<ReleaseRating> findByUser_Id(Integer id);
    Optional<ReleaseRating> findByUser_IdAndMbid(Integer id, String mbid);
}
