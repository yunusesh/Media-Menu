package product.scrobble.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.Query;
import product.scrobble.ScrobbleRepository;
import product.scrobble.model.Scrobble;
import product.scrobble.model.ScrobbleDTO;

import java.util.Optional;

@Service
public class GetScrobbleService implements Query<Integer, ScrobbleDTO> {

    private final ScrobbleRepository scrobbleRepository;

    public GetScrobbleService(ScrobbleRepository scrobbleRepository) {
        this.scrobbleRepository = scrobbleRepository;
    }

    @Override
    public ResponseEntity<ScrobbleDTO> execute (Integer id){
        Optional<Scrobble> scrobbleOptional = scrobbleRepository.findById(id);
        if(scrobbleOptional.isPresent()){
            return ResponseEntity.ok(new ScrobbleDTO(scrobbleOptional.get()));
        }

        return null;
    }
}
