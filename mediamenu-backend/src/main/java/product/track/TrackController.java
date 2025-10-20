package product.track;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.track.model.Track;
import product.track.model.TrackDTO;
import product.track.services.CreateTrackService;
import product.track.services.DeleteTrackService;
import product.track.services.GetTrackService;

@RestController
public class TrackController {
    private final CreateTrackService createTrackService;
    private final DeleteTrackService deleteTrackService;
    private final GetTrackService getTrackService;

    public TrackController(CreateTrackService createTrackService,
                           DeleteTrackService deleteTrackService,
                           GetTrackService getTrackService) {
        this.createTrackService = createTrackService;
        this.deleteTrackService = deleteTrackService;
        this.getTrackService = getTrackService;
    }

    @PostMapping("/api/track")
    public ResponseEntity<TrackDTO> createTrack(@RequestBody Track track){
        return createTrackService.execute(track);
    }

    @GetMapping("/api/track/{id}")
    public ResponseEntity<TrackDTO> getTrackById(@PathVariable Integer id){
        return getTrackService.execute(id);
    }

    @DeleteMapping("/api/track/{id}")
    public ResponseEntity<Void> deleteTrackById(@PathVariable Integer id){
        return deleteTrackService.execute(id);
    }

}
