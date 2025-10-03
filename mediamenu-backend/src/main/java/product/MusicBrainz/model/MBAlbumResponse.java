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
    private String image; // # TO-DO

    private List<MBTrackDTO> tracks;

    private MBMediaDTO[] media;

    @JsonProperty("artist-credit")
    private List<MBArtistDTO> artistCredit;

    @JsonProperty("primary-type")
    private String primaryType;
}

