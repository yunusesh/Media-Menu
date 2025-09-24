package product.MusicBrainz.model;

import lombok.Data;

@Data
public class MBArtistResponse {
    private String id;
    private String name;
    private String year; //year of first release
    private String genre;
    private String imageurl;
}
