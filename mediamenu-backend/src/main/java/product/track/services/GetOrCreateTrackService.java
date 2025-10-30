package product.track.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.track.TrackRepository;
import product.track.model.Track;
import product.track.model.TrackDTO;
import product.track.model.TrackRequestDTO;

@Service
public class GetOrCreateTrackService {
    private TrackRepository trackRepository;

    public GetOrCreateTrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public ResponseEntity<TrackDTO> execute(String trackMbid, String trackTitle, String releaseDate,
                                            String releaseMbid, String releaseTitle, String format,
                                            String artistMbid, String artistName) {
        Track track = trackRepository.upsertTrack(
                trackMbid,
                trackTitle,
                releaseDate,
                releaseMbid,
                releaseTitle,
                format,
                artistMbid,
                artistName
        );

        return ResponseEntity.ok(new TrackDTO(track));
    }

}
