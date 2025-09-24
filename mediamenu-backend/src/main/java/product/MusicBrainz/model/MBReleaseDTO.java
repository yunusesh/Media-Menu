package product.MusicBrainz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MBReleaseDTO {
    @JsonProperty("release-group")
    private MBAlbumDTO releaseGroup;
}
//holy slop i have to find a way to delete this file