package product.artist.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "mbid")
    private String mbid;

    @Column(name = "artist_name")
    private String artistName;

}
