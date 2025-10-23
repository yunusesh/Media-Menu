package product.track.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "track")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "mbid")
    private String mbid;

    @Column(name = "release_id")
    private Integer releaseId;

    @Column(name = "artist_id")
    private Integer artistId;

    @Column(name = "title")
    private String title;
}
