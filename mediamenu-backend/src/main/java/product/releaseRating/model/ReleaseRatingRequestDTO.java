package product.releaseRating.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReleaseRatingRequestDTO {
    private Integer userId;
    private Integer releaseId;
    private Integer rating;
    private Timestamp ratedAt;
    private String releaseMbid;
    private String title;
    private String format;
    private Integer artistId;
    private String artistMbid;
    private String artistName;

    public ReleaseRatingRequestDTO(ReleaseRating rating){
        this.userId = rating.getId().getUserId();
        this.releaseId = rating.getId().getReleaseId();
        this.rating = rating.getRating();
        this.ratedAt = rating.getRatedAt();
        this.releaseMbid = rating.getRelease().getMbid();
        this.title = rating.getRelease().getTitle();
        this.format = rating.getRelease().getFormat();
        this.artistId = rating.getRelease().getArtistId();
        this.artistMbid = rating.getRelease().getArtist().getMbid();
        this.artistName = rating.getRelease().getArtist().getArtistName();
    }
}
