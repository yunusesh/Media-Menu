package product.MusicBrainz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MBAlbumDTO {
    private String title;

    @JsonProperty("artist-credit")
    private List<MBArtistDTO> artistCredit;
}
