package product.MusicBrainz.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import product.MusicBrainz.model.MBAlbumDTO;
import product.MusicBrainz.model.MBAlbumResponse;
import product.MusicBrainz.model.MBArtistDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import product.MusicBrainz.model.MBReleaseResponse;
import product.Query;

import java.util.List;

@Service
public class MBAlbumService implements Query<String, MBAlbumDTO> {

    private final RestTemplate restTemplate;
    private final String url = "https://musicbrainz.org/ws/2/release/";
    private final String coverArtUrl = "http://coverartarchive.org/release/";

    public MBAlbumService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<MBAlbumDTO> execute(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "MediaMenu/1.0 (yunuseshesh@gmail.com)");
    //set user agent header so i dont get banned from the api
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<MBAlbumResponse> response = restTemplate.exchange(
                url + id + "?inc=recordings+artists&fmt=json",
                HttpMethod.GET,
                entity,
                MBAlbumResponse.class
        );

        ResponseEntity<MBReleaseResponse> imageResponse = restTemplate.exchange(
                coverArtUrl + id,
                HttpMethod.GET,
                entity,
                MBReleaseResponse.class
        );

        String imageUrl = imageResponse.getBody().getImages().get(0).getImage();
    //json -> find artist credit list -> return all artists in that list
        assert response.getBody() != null;
        List<MBArtistDTO> artists = response.getBody().getArtistCredit().stream()
                .map(artist -> new MBArtistDTO(artist.getName()))
                .toList();

        MBAlbumDTO mbAlbumDTO = new MBAlbumDTO(
                    response.getBody().getTitle(),
                    response.getBody().getId(),
                    response.getBody().getDate(),
                    imageUrl,
                    artists
            );
            return ResponseEntity.ok(mbAlbumDTO);
    }
}
