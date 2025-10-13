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
    private String id;
    private String title;

    @JsonProperty("first-release-date")
    private String date;

    @JsonProperty("primary-type")
    private String primaryType;

    @JsonProperty("secondary-types")
    private List<String> secondaryTypes;

    private String link; // #TO-DO

    private List<MBTrackDTO> tracklist;

    @JsonProperty("artist-credit")
    private List<MBArtistDTO> artistCredit;

    @JsonProperty("release-group")
    private MBAlbumDTO releaseGroup;

    public MBAlbumDTO(String id, String title, String date, String primaryType, List<String> secondaryTypes){
        this.id = id;
        this.title = title;
        this.date = date;
        this.primaryType = primaryType;
        this.secondaryTypes = secondaryTypes;
    }

    public MBAlbumDTO(String title, String id, String date,
                      String primaryType, List<String> secondaryTypes, List<MBArtistDTO> artistCredit, List<MBTrackDTO> tracklist){
        this.title = title;
        this.id = id;
        this.date = date;
        this.primaryType = primaryType;
        this.artistCredit = artistCredit;
        this.tracklist = tracklist;
        this.secondaryTypes = secondaryTypes;
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