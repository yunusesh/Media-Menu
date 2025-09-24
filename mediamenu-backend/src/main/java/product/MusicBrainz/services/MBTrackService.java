package product.MusicBrainz.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import product.MusicBrainz.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import product.Query;

@Service
public class MBTrackService implements Query<String, MBTrackDTO> {

    private final RestTemplate restTemplate;
    private final String url = "https://musicbrainz.org/ws/2/recording/";

    public MBTrackService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<MBTrackDTO> execute(String id) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "MediaMenu/1.0 (yunuseshesh@gmail.com)");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<MBTrackResponse> response = restTemplate.exchange(
                url + id + "?inc=artist-credits+releases+release-groups&fmt=json",
                HttpMethod.GET,
                entity,
                MBTrackResponse.class
        );
//json -> releases -> first release is usually the main album, if this doesnt hold true then i will check primary type
        MBReleaseDTO release = response.getBody().getReleases().get(0);

        MBAlbumDTO album = new MBAlbumDTO(release.getReleaseGroup().getTitle(),
                        release.getReleaseGroup()
                                .getArtistCredit().stream()
                                .map(artist -> new MBArtistDTO(artist.getName()))
                                .toList()
        );


        MBTrackDTO mbTrackDTO = new MBTrackDTO(response.getBody().getTitle(), album);
        return ResponseEntity.ok(mbTrackDTO);
    }
}
