package product.discogs;

import product.discogs.model.DiscogsArtistDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import product.Query;

@Service
public class DiscogsArtistService implements Query<Integer, DiscogsArtistDTO> {

    private final RestTemplate restTemplate;

    public DiscogsArtistService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<DiscogsArtistDTO> execute(Integer id) {
        DiscogsArtistResponse response = restTemplate.getForObject("https://api.discogs.com/artists/" + id, DiscogsArtistResponse.class);
        DiscogsArtistDTO discogsArtistDTO = new DiscogsArtistDTO(response.getName());
        System.out.println("requesting" + id);
        return ResponseEntity.ok(discogsArtistDTO);
    }
}
