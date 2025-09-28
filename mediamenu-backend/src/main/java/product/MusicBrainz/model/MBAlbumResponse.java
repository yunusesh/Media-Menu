package product.MusicBrainz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class MBAlbumResponse {
    private String title;
    private String id;
    private String date;
    private String link; // #TO-DO
    private String imageurl; // # TO-DO

    @JsonProperty("artist-credit")
    private List<MBArtistDTO> artistCredit;

    @JsonProperty("release-group")
    private MBAlbumDTO releaseGroup;

    @JsonProperty("release-groups")
    private List<MBReleaseDTO> releaseGroups;

    @JsonProperty("primary-type")
    private String primaryType;
}
