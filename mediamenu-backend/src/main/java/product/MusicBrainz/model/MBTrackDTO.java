package product.MusicBrainz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MBTrackDTO {
    private String title;
    private String id;
    private int position;

    @JsonProperty("artist-credit")
    private List<MBArtistDTO> artistCredit;

    public MBTrackDTO(String title, String id, int position){
        this.title = title;
        this.id = id;
        this.position = position;
    }
}
