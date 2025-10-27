package product.release.model;

import lombok.Data;

@Data
public class ReleaseDTO {
    private Integer id;
    private String mbid;
    private Integer artistId;
    private String title;
    private String format;

    public ReleaseDTO(Release release){
        this.id = release.getId();
        this.mbid = release.getMbid();
        this.artistId = release.getArtistId();
        this.title = release.getTitle();
        this.format = release.getFormat();
    }
}
