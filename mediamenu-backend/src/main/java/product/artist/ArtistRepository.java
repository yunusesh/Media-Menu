package product.artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.artist.model.Artist;

@Repository

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
}
