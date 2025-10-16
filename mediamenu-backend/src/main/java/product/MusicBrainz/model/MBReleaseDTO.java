package product.MusicBrainz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MBReleaseDTO {
    private String title;
    private String releaseGroupId;
    private String id;
    private String date;
    public String disambiguation;

    @JsonProperty("artist-credit")
    private List<MBArtistDTO> artistCredit;

    private List<MBAlbumDTO> releases;

    public MBReleaseDTO(String releaseGroupId, String title, List<MBArtistDTO> artistCredit, String id){
        this.releaseGroupId = releaseGroupId;
        this.title = title;
        this.artistCredit = artistCredit;
        this.id = id;
    }

    public MBReleaseDTO(String id, String date) {
        this.id = id;
        this.date = date;
    }

    public MBReleaseDTO(String title, String id, String disambiguation){
        this.title = title;
        this.id = id;
        this.disambiguation = disambiguation;
    }
}
