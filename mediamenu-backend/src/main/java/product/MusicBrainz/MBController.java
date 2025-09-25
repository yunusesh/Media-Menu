package product.MusicBrainz;

import product.MusicBrainz.model.MBAlbumDTO;
import product.MusicBrainz.model.MBArtistDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.MusicBrainz.model.MBTrackDTO;
import product.MusicBrainz.model.SearchArtistDTO;
import product.MusicBrainz.services.MBAlbumService;
import product.MusicBrainz.services.MBArtistService;
import product.MusicBrainz.services.MBTrackService;
import product.MusicBrainz.services.SearchArtistService;

import java.util.List;

@RestController
public class MBController {
    private final MBArtistService mbArtistService;
    private final MBAlbumService mbAlbumService;
    private final MBTrackService mbTrackService;
    private final SearchArtistService searchArtistService;

    public MBController(MBArtistService mbArtistService,
                        MBAlbumService mbAlbumService,
                        MBTrackService mbTrackService,
                        SearchArtistService searchArtistService)
    {
        this.mbArtistService = mbArtistService;
        this.mbAlbumService = mbAlbumService;
        this.mbTrackService = mbTrackService;
        this.searchArtistService = searchArtistService;
    }

    @GetMapping("/artist")
    public ResponseEntity<MBArtistDTO> getArtist(@PathVariable String id){

       return mbArtistService.execute(id);
    }

    @GetMapping("/album/{id}")
    public ResponseEntity<MBAlbumDTO> getAlbum(@PathVariable String id){

        return mbAlbumService.execute(id);
    }

    @GetMapping("/track/{id}")
    public ResponseEntity<MBTrackDTO> getTrack(@PathVariable String id) {

        return mbTrackService.execute(id);
    }

    @GetMapping("/artists/{name}")
    public ResponseEntity<SearchArtistDTO> searchArtist(@PathVariable String name){
        return searchArtistService.execute(name);
        }
}
