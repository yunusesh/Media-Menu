package product.MusicBrainz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MBAlbumResponse {
    private String id;
    private String title;

    @JsonProperty("artist-credit")
    private List<MBArtistDTO> artistCredit;

    @JsonProperty("release-groups")
    private List<MBAlbumDTO> releaseGroups;
}
