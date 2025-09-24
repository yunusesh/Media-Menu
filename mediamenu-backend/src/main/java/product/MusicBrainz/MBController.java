package product.MusicBrainz;

import product.MusicBrainz.model.MBAlbumDTO;
import product.MusicBrainz.model.MBArtistDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.MusicBrainz.model.MBTrackDTO;
import product.MusicBrainz.services.MBAlbumService;
import product.MusicBrainz.services.MBArtistService;
import product.MusicBrainz.services.MBTrackService;

@RestController
public class MBController {
    private final MBArtistService mbArtistService;
    private final MBAlbumService mbAlbumService;
    private final MBTrackService mbTrackService;

    public MBController(MBArtistService mbArtistService,
                        MBAlbumService mbAlbumService,
                        MBTrackService mbTrackService) {
        this.mbArtistService = mbArtistService;
        this.mbAlbumService = mbAlbumService;
        this.mbTrackService = mbTrackService;
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<MBArtistDTO> getArtist(@PathVariable String id){

       return mbArtistService.execute(id);
    }

    @GetMapping("/albums/{id}")
    public ResponseEntity<MBAlbumDTO> getAlbum(@PathVariable String id){

        return mbAlbumService.execute(id);
    }

    @GetMapping("/tracks/{id}")
    public ResponseEntity<MBTrackDTO> getTrack(@PathVariable String id) {

        return mbTrackService.execute(id);
    }
}
