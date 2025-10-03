package product.MusicBrainz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class MBReleaseDTO {
    private String title;
    private String id;

    @JsonProperty("artist-credit")
    private List<MBArtistDTO> artistCredit;

    private List<MBAlbumDTO> releases;

    public MBReleaseDTO(String title, List<MBArtistDTO> artistCredit, String id){
        this.title = title;
        this.artistCredit = artistCredit;
        this.id = id;
    }

}
