package product.trackRating.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "track_rating")
@NoArgsConstructor
@AllArgsConstructor
public class TrackRating {
    @EmbeddedId
    private TrackRatingId id;
    
    @Column(name = "mbid")
    private String mbid;

    @Column(name = "rating")
    private Integer rating;

    @CreationTimestamp
    @Column(name = "rated_at")
    private Timestamp ratedAt;
}
