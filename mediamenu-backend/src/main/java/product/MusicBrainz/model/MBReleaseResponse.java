package product.MusicBrainz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
//#TO-DO refactor most of these DTOs to be children of release
public class MBReleaseResponse {
    private String title;
    public List <MBAlbumDTO> images;

    @JsonProperty("cover-art-archive")
    private MBCoverArtArchiveDTO coverArtArchive;

    @JsonProperty("artist-credit")
    private List<MBArtistDTO> artistCredit;

    private List<MBAlbumDTO> releases;
}