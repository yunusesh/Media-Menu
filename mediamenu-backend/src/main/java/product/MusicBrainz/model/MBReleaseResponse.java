package product.MusicBrainz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MBReleaseResponse {
    private String id;

    @JsonProperty("release-group")
    private List<MBAlbumDTO> releaseGroup;

    private String name;
    private String title;
}
