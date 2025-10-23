package product.scrobble.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "scrobble")
public class Scrobble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "track_id")
    private Integer trackId;

    @Column(name = "scrobbles")
    private Integer scrobbles;

    @CreationTimestamp
    @Column(name = "listened_at")
    private Timestamp listenedAt; //make a list?

    @CreationTimestamp
    @Column(name = "first_listened_at")
    private Timestamp firstListenedAt;
}
