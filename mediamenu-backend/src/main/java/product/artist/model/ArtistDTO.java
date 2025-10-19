package product.artist.model;

import lombok.Data;

@Data
public class ArtistDTO {
    private Integer id;
    private String mbid;
    private String artistName;

    public ArtistDTO(Artist artist) {
        this.id = artist.getId();
        this.mbid = artist.getMbid();
        this.artistName = artist.getArtistName();
    }
}
