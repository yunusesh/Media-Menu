package product.scrobble;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.scrobble.model.Scrobble;
import product.scrobble.model.ScrobbleDTO;
import product.scrobble.services.CreateScrobbleService;
import product.scrobble.services.DeleteScrobbleService;
import product.scrobble.services.GetScrobbleService;

@RestController
public class ScrobbleController {
    private final CreateScrobbleService createScrobbleService;
    private final GetScrobbleService getScrobbleService;
    private final DeleteScrobbleService deleteScrobbleService;

    public ScrobbleController(CreateScrobbleService createScrobbleService,
                              GetScrobbleService getScrobbleService,
                              DeleteScrobbleService deleteScrobbleService) {
        this.createScrobbleService = createScrobbleService;
        this.getScrobbleService = getScrobbleService;
        this.deleteScrobbleService = deleteScrobbleService;
    }

    @PostMapping("/api/scrobble")
    public ResponseEntity<ScrobbleDTO> createScrobble(@RequestBody Scrobble scrobble){
        return createScrobbleService.execute(scrobble);
    }

    @GetMapping("/api/scrobble/{id}")
    public ResponseEntity<ScrobbleDTO> getScrobbleById(@PathVariable Integer id){
        return getScrobbleService.execute(id);
    }

    @DeleteMapping("/api/scrobble/{id}")
    public ResponseEntity<Void> deleteScrobble(@PathVariable Integer id){
        return deleteScrobbleService.execute(id);
    }
}
