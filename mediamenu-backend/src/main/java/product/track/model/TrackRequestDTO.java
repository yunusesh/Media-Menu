package product.track.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackRequestDTO {
    private Integer trackId;
    private String trackMbid;
    private String trackTitle;
    private String releaseDate;
    private Integer releaseId;
    private String releaseMbid;
    private String releaseTitle;
    private String format;
    private Integer artistId;
    private String artistMbid;
    private String artistName;


    public TrackRequestDTO(Track track) {
        this.trackId = track.getId();
        this.trackMbid = track.getMbid();
        this.trackTitle = track.getTitle();
        this.releaseId = track.getRelease().getId();
        this.releaseMbid = track.getRelease().getMbid();
        this.releaseTitle = track.getRelease().getTitle();
        this.format = track.getRelease().getFormat();
        this.artistId = track.getArtist().getId();
        this.artistMbid = track.getArtist().getMbid();
        this.artistName = track.getArtist().getArtistName();
        this.releaseDate = track.getReleaseDate();
    }
}
