package product.releaseRating.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import product.user.model.AppUser;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "release_rating")
@NoArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY) //fetch user only when accessed
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private AppUser user;


    public ReleaseRating(ReleaseRatingId id, String mbid, Integer rating, Timestamp ratedAt){
        this.id = id;
        this.mbid = mbid;
        this.rating = rating;
        this.ratedAt = ratedAt;
    }
}
