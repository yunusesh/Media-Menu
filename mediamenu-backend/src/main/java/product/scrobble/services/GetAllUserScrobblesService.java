package product.scrobble.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.Query;
import product.scrobble.ScrobbleRepository;
import product.scrobble.model.ScrobbleRequestDTO;

import java.util.List;

@Service
public class GetAllUserScrobblesService implements Query<Integer, List<ScrobbleRequestDTO>> {
    private ScrobbleRepository scrobbleRepository;

    public GetAllUserScrobblesService(ScrobbleRepository scrobbleRepository) {
        this.scrobbleRepository = scrobbleRepository;
    }

    @Override
    public ResponseEntity<List<ScrobbleRequestDTO>> execute(Integer userId) {
        return ResponseEntity.ok(scrobbleRepository.findAllByUserId(userId));
    }
}
