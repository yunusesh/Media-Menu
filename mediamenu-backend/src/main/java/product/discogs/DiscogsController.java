package product.discogs;

import product.discogs.model.DiscogsArtistDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DiscogsController {
    private final DiscogsArtistService discogsArtistService;

        public DiscogsController(DiscogsArtistService discogsArtistService) {
        this.discogsArtistService = discogsArtistService;
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<DiscogsArtistDTO> getArtist(@PathVariable Integer id){
       return discogsArtistService.execute(id);
    }
}
