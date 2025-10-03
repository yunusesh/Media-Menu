package product.MusicBrainz.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MBMediaDTO {
    private List<MBTrackDTO> tracks;
}
