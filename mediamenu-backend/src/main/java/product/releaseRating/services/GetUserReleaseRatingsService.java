package product.releaseRating.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.Query;
import product.releaseRating.ReleaseRatingRepository;
import product.releaseRating.model.ReleaseRating;
import product.releaseRating.model.ReleaseRatingDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetUserReleaseRatingsService implements Query<Integer, List<ReleaseRatingDTO>> {
    private ReleaseRatingRepository releaseRatingRepository;

    public GetUserReleaseRatingsService(ReleaseRatingRepository releaseRatingRepository) {
        this.releaseRatingRepository = releaseRatingRepository;
    }

    @Override
    public ResponseEntity<List<ReleaseRatingDTO>> execute(Integer userId) {
        List<ReleaseRatingDTO> userRatings = releaseRatingRepository.findByUser_Id(userId).stream()
                .map(rating -> new ReleaseRatingDTO(rating))
                .toList();

        return ResponseEntity.ok(userRatings);
    }
}
