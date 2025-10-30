package product.track;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.track.model.Track;
import product.track.model.TrackDTO;
import product.track.model.TrackRequestDTO;
import product.track.services.CreateTrackService;
import product.track.services.DeleteTrackService;
import product.track.services.GetOrCreateTrackService;
import product.track.services.GetTrackService;

@RestController
public class TrackController {
    private final CreateTrackService createTrackService;
    private final DeleteTrackService deleteTrackService;
    private final GetTrackService getTrackService;
    private final GetOrCreateTrackService getOrCreateTrackService;

    public TrackController(CreateTrackService createTrackService,
                           DeleteTrackService deleteTrackService,
                           GetTrackService getTrackService,
                           GetOrCreateTrackService getOrCreateTrackService) {
        this.createTrackService = createTrackService;
        this.deleteTrackService = deleteTrackService;
        this.getTrackService = getTrackService;
        this.getOrCreateTrackService = getOrCreateTrackService;
    }

    @PostMapping("/api/track")
    public ResponseEntity<TrackDTO> createTrack(@RequestBody Track track) {
        return createTrackService.execute(track);
    }

    @GetMapping("/api/track/{id}")
    public ResponseEntity<TrackDTO> getTrackById(@PathVariable Integer id) {
        return getTrackService.execute(id);
    }

    @PostMapping("/api/track/getOrCreate")
    public ResponseEntity<TrackDTO> getOrCreateTrack(@RequestBody TrackRequestDTO track) {
        TrackDTO trackDTO = getOrCreateTrackService.execute(
                track.getTrackMbid(),
                track.getTrackTitle(),
                track.getReleaseDate(),
                track.getReleaseMbid(),
                track.getReleaseTitle(),
                track.getFormat(),
                track.getArtistMbid(),
                track.getArtistName()
        ).getBody();

        return ResponseEntity.ok(trackDTO);
    }

    @DeleteMapping("/api/track/{id}")
    public ResponseEntity<Void> deleteTrackById(@PathVariable Integer id) {
        return deleteTrackService.execute(id);
    }

}
