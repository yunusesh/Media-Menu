package product.track.model;

import lombok.Data;

@Data
public class TrackDTO {
    private Integer id;
    private String mbid;
    private Integer releaseId;
    private Integer artistId;
    private String releaseTitle;
    private String artistName;
    private String title;

    public TrackDTO(Track track) {
        this.id = track.getId();
        this.mbid = track.getMbid();
        this.releaseId = track.getReleaseId();
        this.artistId = track.getArtistId();
        this.releaseTitle = track.getReleaseTitle();
        this.artistName = track.getArtistName();
        this.title = track.getTitle();
    }
}
