package product.MusicBrainz.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import product.MusicBrainz.model.MBAlbumDTO;
import product.MusicBrainz.model.MBArtistDTO;
import product.MusicBrainz.model.MBTrackDTO;
import product.MusicBrainz.model.MBTrackResponse;
import product.Query;

import java.util.List;

@Service
public class MBTrackService implements Query<String, MBTrackDTO> {

    private final RestTemplate restTemplate;

    public MBTrackService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable(value= "getTrackCache", cacheManager="getCacheManager")
    public ResponseEntity<MBTrackDTO> execute (String id){
        final String url = "https://musicbrainz.org/ws/2/recording/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "MediaMenu/1.0 (yunuseshesh@gmail.com)");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<MBTrackResponse> response = restTemplate.exchange(
                url + id + "?inc=artist-credits+releases+release-groups&fmt=json",
                HttpMethod.GET,
                entity,
                MBTrackResponse.class
        );


        MBAlbumDTO release = response.getBody().getReleases().get(0).getReleaseGroup();

        List<MBArtistDTO> artists = response.getBody().getArtistCredit().stream()
                .map(artist -> new MBArtistDTO(artist.getArtist().getName(), artist.getArtist().getId()))
                .toList();

        MBTrackDTO mbTrackDTO = new MBTrackDTO(
                response.getBody().getId(),
                response.getBody().getTitle(),
                response.getBody().getDate(),
                release,
                artists
        );
        return ResponseEntity.ok(mbTrackDTO);
    }
}
