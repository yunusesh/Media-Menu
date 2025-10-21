package product.trackRating;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.trackRating.model.*;
import product.trackRating.services.CreateTrackRatingService;
import product.trackRating.services.DeleteTrackRatingService;
import product.trackRating.services.GetTrackRatingService;
import product.trackRating.services.UpdateTrackRatingService;

@RestController
public class TrackRatingController {
    private final CreateTrackRatingService createTrackRatingService;
    private final DeleteTrackRatingService deleteTrackRatingService;
    private final GetTrackRatingService getTrackRatingService;
    private final UpdateTrackRatingService updateTrackRatingService;

    public TrackRatingController(CreateTrackRatingService createTrackRatingService,
                                 DeleteTrackRatingService deleteTrackRatingService,
                                 GetTrackRatingService getTrackRatingService,
                                 UpdateTrackRatingService updateTrackRatingService) {
        this.createTrackRatingService = createTrackRatingService;
        this.deleteTrackRatingService = deleteTrackRatingService;
        this.getTrackRatingService = getTrackRatingService;
        this.updateTrackRatingService = updateTrackRatingService;
    }

    @PostMapping("/api/track-rating")
    public ResponseEntity<TrackRatingDTO> createRating (@RequestBody TrackRatingDTO trackRating){
        return createTrackRatingService.execute(trackRating);
    }

    @GetMapping("/api/track-rating/user/{userId}/track/{trackId}")
    public ResponseEntity<TrackRatingDTO> getRatingById(@PathVariable Integer userId, @PathVariable Integer trackId){
        return getTrackRatingService.execute(new TrackRatingKey(userId, trackId));
    }

    @DeleteMapping("/api/track-rating/user/{userId}/track/{trackId}")
    public ResponseEntity<Void> deleteRatingById(@PathVariable Integer userId, @PathVariable Integer trackId){
        return deleteTrackRatingService.execute(new TrackRatingKey(userId, trackId));
    }

    @PutMapping("/api/track-rating/user/{userId}/track/{trackId}")
    public ResponseEntity<TrackRatingDTO> updateRating(@PathVariable Integer userId,
                                                       @PathVariable Integer trackId,
                                                       @RequestBody TrackRating trackRating){
        return updateTrackRatingService.execute(new UpdateTrackRatingCommand(userId, trackId, trackRating));
    }

}
