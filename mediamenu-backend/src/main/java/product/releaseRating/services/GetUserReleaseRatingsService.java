package product.releaseRating.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.Query;
import product.releaseRating.ReleaseRatingRepository;
import product.releaseRating.model.ReleaseRating;
import product.releaseRating.model.ReleaseRatingDTO;
import product.releaseRating.model.ReleaseRatingRequestDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetUserReleaseRatingsService implements Query<Integer, List<ReleaseRatingRequestDTO>>{
    private ReleaseRatingRepository releaseRatingRepository;

    public GetUserReleaseRatingsService(ReleaseRatingRepository releaseRatingRepository) {
        this.releaseRatingRepository = releaseRatingRepository;
    }

    @Override
    public ResponseEntity<List<ReleaseRatingRequestDTO>> execute(Integer userId) {

        return ResponseEntity.ok(releaseRatingRepository.findAllByUserId(userId));
    }
}
