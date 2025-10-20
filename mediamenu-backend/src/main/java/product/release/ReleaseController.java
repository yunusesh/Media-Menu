package product.release;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.release.model.Release;
import product.release.model.ReleaseDTO;
import product.release.services.CreateReleaseService;
import product.release.services.DeleteReleaseService;
import product.release.services.GetReleaseService;

@RestController
public class ReleaseController {
    private final CreateReleaseService createReleaseService;
    private final DeleteReleaseService deleteReleaseService;
    private final GetReleaseService getReleaseService;

    public ReleaseController(CreateReleaseService createReleaseService,
                             DeleteReleaseService deleteReleaseService,
                             GetReleaseService getReleaseService) {
        this.createReleaseService = createReleaseService;
        this.deleteReleaseService = deleteReleaseService;
        this.getReleaseService = getReleaseService;
    }

    @PostMapping("/api/release")
    public ResponseEntity<ReleaseDTO> createRelease(@RequestBody Release release){
        return createReleaseService.execute(release);
    }

    @GetMapping("/api/release/{id}")
    public ResponseEntity<ReleaseDTO> getReleaseById(@PathVariable Integer id){
        return getReleaseService.execute(id);
    }

    @DeleteMapping("/api/release/{id}")
    public ResponseEntity<Void> deleteRelease(@PathVariable Integer id){
        return deleteReleaseService.execute(id);
    }


}
