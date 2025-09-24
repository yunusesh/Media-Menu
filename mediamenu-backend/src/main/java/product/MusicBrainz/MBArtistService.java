package product.MusicBrainz;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import product.MusicBrainz.model.MBArtistDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import product.Query;
import product.MusicBrainz.model.MBArtistResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@Service
public class MBArtistService implements Query<String, MBArtistDTO> {

    private final RestTemplate restTemplate;
    private final String url = "https://musicbrainz.org/ws/2/artist/";

    public MBArtistService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<MBArtistDTO> execute(String id) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "MediaMenu/1.0 (yunuseshesh@gmail.com)");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<MBArtistResponse> response = restTemplate.exchange(
                url + id + "?inc=aliases&fmt=json",
                HttpMethod.GET,
                entity,
                MBArtistResponse.class
        );

        MBArtistDTO mbArtistDTO = new MBArtistDTO(response.getBody().getName());
        return ResponseEntity.ok(mbArtistDTO);
    }
}
