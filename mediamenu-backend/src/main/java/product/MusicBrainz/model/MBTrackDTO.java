package product.MusicBrainz.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MBTrackDTO {
    private String title;
    private String artist;
    private String album;
}
