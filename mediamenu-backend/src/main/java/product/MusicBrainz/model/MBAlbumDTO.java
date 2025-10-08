package product.MusicBrainz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
//here im not gonna use @AllArgsConstructor b/c i need to create more than 1 possible constructor

public class MBAlbumDTO {
    private String title;
    private String id;
    @JsonProperty("first-release-date")
    private String date;
    private String link; // #TO-DO

    private List<MBTrackDTO>tracks;

    @JsonProperty("artist-credit")
    private List<MBArtistDTO> artistCredit;

    @JsonProperty("primary-type")
    private String primaryType;

    public MBAlbumDTO(String id, String title){
        this.id = id;
        this.title = title;
    }

    public MBAlbumDTO(String title, String id, String date, List<MBArtistDTO> artistCredit, List<MBTrackDTO> tracks) {
        this.title = title;
        this.id = id;
        this.date = date;
        this.artistCredit = artistCredit;
        this.tracks = tracks;
    }

    public MBAlbumDTO(String title, String id, String date, List<MBArtistDTO> artistCredit) {
        this.title = title;
        this.id = id;
        this.date = date;
        this.artistCredit = artistCredit;
    }

    public MBAlbumDTO(String title, String id, List<MBArtistDTO> artistCredit){
        this.title = title;
        this.id = id;
        this.artistCredit = artistCredit;
    }

    public MBAlbumDTO(String title, List<MBArtistDTO> artistCredit){
        this.title = title;
        this.artistCredit = artistCredit;
    }

}