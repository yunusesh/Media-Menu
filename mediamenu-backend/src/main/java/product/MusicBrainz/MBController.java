package product.MusicBrainz;

import product.MusicBrainz.model.MBAlbumDTO;
import product.MusicBrainz.model.MBArtistDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MBController {
    private final MBArtistService MBArtistService;
    private final MBAlbumService MBAlbumService;

    public MBController(MBArtistService MBArtistService,
                        MBAlbumService MBAlbumService) {
        this.MBArtistService = MBArtistService;
        this.MBAlbumService = MBAlbumService;
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<MBArtistDTO> getArtist(@PathVariable String id){

       return MBArtistService.execute(id);
    }

    @GetMapping("/albums/{id}")
    public ResponseEntity<MBAlbumDTO> getAlbum(@PathVariable String id){

        return MBAlbumService.execute(id);
    }
}
