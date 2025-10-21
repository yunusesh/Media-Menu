package product.releaseRating.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class ReleaseRatingDTO {
    private Integer userId;
    private Integer releaseId;
    private String mbid;
    private Integer rating;
    private Timestamp ratedAt;

    public ReleaseRatingDTO(ReleaseRating rating) {
        this.userId = rating.getId().getUserId();
        this.releaseId = rating.getId().getReleaseId();
        this.mbid = rating.getMbid();
        this.rating = rating.getRating();
        this.ratedAt = rating.getRatedAt();
    }
}
