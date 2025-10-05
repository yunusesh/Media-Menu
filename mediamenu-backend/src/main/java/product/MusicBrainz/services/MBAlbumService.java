    package product.MusicBrainz.services;

    import org.springframework.cache.annotation.CacheConfig;
    import org.springframework.cache.annotation.Cacheable;
    import org.springframework.http.HttpEntity;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.HttpMethod;
    import product.MusicBrainz.model.*;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;
    import product.Query;

    import java.util.Arrays;
    import java.util.List;

    @Service
    public class MBAlbumService implements Query<String, MBAlbumDTO> {

        private final RestTemplate restTemplate;

        public MBAlbumService(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        @Override
        @Cacheable(value = "getAlbumCache", cacheManager = "getCacheManager")
        public ResponseEntity<MBAlbumDTO> execute(String id) {
            final String url = "https://musicbrainz.org/ws/2/release/";

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
            //json -> find artist credit list -> return all artists in that list

            List<MBArtistDTO> artists = response.getBody().getArtistCredit().stream()
                    .map(artist -> new MBArtistDTO(artist.getName()))
                    .toList();

            List<MBTrackDTO> tracklist = response.getBody().getMedia().stream()
                    .flatMap(media -> media.getTracks().stream())
                    .toList();

            MBAlbumDTO mbAlbumDTO = new MBAlbumDTO(
                    response.getBody().getTitle(),
                    response.getBody().getId(),
                    response.getBody().getDate(),
                    artists,
                    tracklist
            );
            return ResponseEntity.ok(mbAlbumDTO);
        }
    }