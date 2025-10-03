package product.MusicBrainz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class MBTrackResponse {
    private String title;
    private String id;

    @JsonProperty("artist-credit")
    private List<MBArtistDTO> artistCredit;
}
