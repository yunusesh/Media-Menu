package product.track.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.Query;
import product.track.TrackRepository;
import product.track.model.Track;

import java.util.Optional;

@Service
public class DeleteTrackService implements Query<Integer, Void> {

    private final TrackRepository trackRepository;

    public DeleteTrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public ResponseEntity<Void> execute (Integer id){
        Optional<Track> trackOptional = trackRepository.findById(id);
        if(trackOptional.isPresent()){
            trackRepository.delete(trackOptional.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return null;
    }
}
