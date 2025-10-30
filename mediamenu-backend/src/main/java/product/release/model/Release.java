package product.release.model;

import jakarta.persistence.*;
import lombok.Data;
import product.artist.model.Artist;

@Entity
@Data
@Table(name = "releaseGroup")
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "mbid")
    private String mbid;

    @Column(name = "artist_id")
    private Integer artistId;

    @Column(name = "title")
    private String title;

    @Column(name = "format")
    private String format;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", insertable = false, updatable = false)
    Artist artist;

}
