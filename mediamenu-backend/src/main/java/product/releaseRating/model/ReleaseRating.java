package product.releaseRating.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "release_rating")
@NoArgsConstructor
@AllArgsConstructor
public class ReleaseRating {
    @EmbeddedId
    private ReleaseRatingId id;

    @Column(name = "mbid")
    private String mbid;

    @Column(name = "rating")
    private Integer rating;

    @CreationTimestamp
    @Column(name = "rated_at")
    private Timestamp ratedAt;
}
