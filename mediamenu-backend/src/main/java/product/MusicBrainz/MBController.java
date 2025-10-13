package product.MusicBrainz;

import product.MusicBrainz.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.MusicBrainz.services.*;

import java.util.List;

@RestController
public class MBController {
    private final MBArtistService mbArtistService;
    private final MBAlbumService mbAlbumService;
    private final MBTrackService mbTrackService;
    private final SearchArtistService searchArtistService;
    private final SearchReleaseService searchReleaseService;

    public MBController(MBArtistService mbArtistService,
                        MBAlbumService mbAlbumService,
                        MBTrackService mbTrackService,
                        SearchArtistService searchArtistService,
                        SearchReleaseService searchReleaseService)
    {
        this.mbArtistService = mbArtistService;
        this.mbAlbumService = mbAlbumService;
        this.mbTrackService = mbTrackService;
        this.searchArtistService = searchArtistService;
        this.searchReleaseService = searchReleaseService;
    }

    @GetMapping("/artist/{id}")
    public ResponseEntity<MBArtistDTO> getArtist(@PathVariable String id){
       return mbArtistService.execute(id);
    }

    @GetMapping("/album/{id}")
    public ResponseEntity<MBAlbumDTO> getAlbum(@PathVariable String id){
        return mbAlbumService.execute(id);
    }

    @GetMapping("/track/{id}")
    public ResponseEntity<MBTrackDTO> getTrack(@PathVariable String id){
        return mbTrackService.execute(id);
    }

    @GetMapping("/artists/{name}")
    public ResponseEntity<SearchArtistDTO> searchArtist(@PathVariable String name){
        return searchArtistService.execute(name);
        }

    @GetMapping("/releases/{title}")
    public ResponseEntity<SearchReleaseDTO> searchRelease(@PathVariable String title){
        return searchReleaseService.execute(title);
    }
}
