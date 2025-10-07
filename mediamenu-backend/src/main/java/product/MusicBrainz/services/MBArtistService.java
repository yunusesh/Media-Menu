package product.MusicBrainz.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import product.MusicBrainz.model.MBAlbumDTO;
import product.MusicBrainz.model.MBArtistDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import product.Query;
import product.MusicBrainz.model.MBArtistResponse;

import java.util.List;

@Service
public class MBArtistService implements Query<String, MBArtistDTO> {
    @Value("${fanart_api_key}")
    private String fanart_api_key;

    private final RestTemplate restTemplate;

    public MBArtistService(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable(value = "getArtistCache", cacheManager = "getCacheManager")
    public ResponseEntity<MBArtistDTO> execute(String id) {

        final String fetchArtist = "https://musicbrainz.org/ws/2/artist/";
        final String fetchImage = "http://webservice.fanart.tv/v3/music/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "MediaMenu/1.0 (yunuseshesh@gmail.com)");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<MBArtistResponse> response = restTemplate.exchange(
                fetchArtist + id + "?inc=release-groups&fmt=json",
                HttpMethod.GET,
                entity,
                MBArtistResponse.class
        );

        String image = null;

        try{
        ResponseEntity<MBArtistResponse> imageResponse = restTemplate.exchange(
                fetchImage + id + "?api_key=" + fanart_api_key,
                HttpMethod.GET,
                entity,
                MBArtistResponse.class
        );

                if(imageResponse.getBody().getArtistthumb() != null) {
                    image = imageResponse.getBody().getArtistthumb().get(0).getUrl();
                }
        }
            catch(HttpClientErrorException.NotFound e) {
                image = null;
            }

        List<MBAlbumDTO> releases = response.getBody().getReleaseGroups().stream()
                .map(release -> new MBAlbumDTO(release.getId(), release.getTitle()))
                .toList();

        MBArtistDTO mbArtistDTO = new MBArtistDTO(
                response.getBody().getId(),
                response.getBody().getName(),
                image,
                releases
        );
        return ResponseEntity.ok(mbArtistDTO);
    }
}
