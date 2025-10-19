package product.artist;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.artist.model.Artist;
import product.artist.model.ArtistDTO;
import product.artist.services.CreateArtistService;
import product.artist.services.DeleteArtistService;
import product.artist.services.GetArtistService;

@RestController
public class ArtistController {
    private final CreateArtistService createArtistService;
    private final GetArtistService getArtistService;
    private final DeleteArtistService deleteArtistService;

    public ArtistController(CreateArtistService createArtistService, GetArtistService getArtistService,
                            DeleteArtistService deleteArtistService) {
        this.createArtistService = createArtistService;
        this.getArtistService = getArtistService;
        this.deleteArtistService = deleteArtistService;
    }

    @PostMapping("/api/artist")
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody Artist artist){
        return createArtistService.execute(artist);
    }

    @GetMapping("/api/artist/{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable Integer id){
        return getArtistService.execute(id);
    }

    @DeleteMapping("/api/artist/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Integer id){
        return deleteArtistService.execute(id);
    }
}