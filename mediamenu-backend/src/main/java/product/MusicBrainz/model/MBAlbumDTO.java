package product.MusicBrainz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
//here im not gonna use @AllArgsConstructor b/c i need to create more than 1 possible constructor

public class MBAlbumDTO {
    private String title;
    private String id;
    private String date;
    private String link; // #TO-DO
    private String imageurl; //#TO-DO

    @JsonProperty("artist-credit")
    private List<MBArtistDTO> artistCredit;

    @JsonProperty("primary-type")
    private String primaryType;

    public MBAlbumDTO(String title, String primaryType, List<MBArtistDTO> artistCredit) {
        this.title = title;
        this.primaryType = primaryType;
        this.artistCredit = artistCredit;
    }

    public MBAlbumDTO(String title, String id, String date, List<MBArtistDTO> artistCredit) {
        this.title = title;
        this.id = id;
        this.date = date;
        this.artistCredit = artistCredit;
    }

    public MBAlbumDTO(String title, List<MBArtistDTO> artistCredit) {
        this.title = title;
        this.artistCredit = artistCredit;
    }


}
