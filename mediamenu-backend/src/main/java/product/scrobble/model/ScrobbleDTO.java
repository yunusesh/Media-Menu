package product.scrobble.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ScrobbleDTO {
    private Integer id;
    private Integer userId;
    private Integer trackId;
    private Timestamp firstListenedAt;

    public ScrobbleDTO(Scrobble scrobble){
        this.id = scrobble.getId();
        this.userId = scrobble.getUserId();
        this.trackId = scrobble.getTrackId();
        this.firstListenedAt = scrobble.getFirstListenedAt();
    }
}
