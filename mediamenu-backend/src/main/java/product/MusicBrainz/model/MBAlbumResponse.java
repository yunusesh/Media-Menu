package product.MusicBrainz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class MBAlbumResponse {
    private String title;
    private String id;
    @JsonProperty("first-release-date")
    private String date;
    private String link; // #TO-DO
    private String image; // # TO-DO

    private List<MBTrackDTO> tracklist;

    private List<MBMediaDTO> media;

    private List<MBReleaseDTO> releases;

    @JsonProperty("artist-credit")
    private List<MBArtistDTO> artistCredit;

    @JsonProperty("primary-type")
    private String primaryType;

    @JsonProperty("secondary-types")
    private List<String> secondaryTypes;
} 