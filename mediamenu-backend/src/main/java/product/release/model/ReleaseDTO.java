package product.release.model;

import lombok.Data;

@Data
public class ReleaseDTO {
    private Integer id;
    private String mbid;
    private Integer artistId;
    private String artistName;
    private String title;

    public ReleaseDTO(Release release){
        this.id = release.getId();
        this.mbid = release.getMbid();
        this.artistId = release.getArtistId();
        this.artistName = release.getArtistName();
        this.title = release.getTitle();
    }
}
