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
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<MBAlbumResponse> response = restTemplate.exchange(
                    url + id + "?inc=recordings+artists&fmt=json",
                    HttpMethod.GET,
                    entity,
                    MBAlbumResponse.class
            );

            List<MBArtistDTO> artists = response.getBody().getArtistCredit().stream()
                    .map(artist -> new MBArtistDTO(artist.getName()))
                    .toList();

            //release json -> tracklist in media list, flatten to get all record sides as one list
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