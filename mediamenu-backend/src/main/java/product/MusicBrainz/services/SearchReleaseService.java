package product.MusicBrainz.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import product.MusicBrainz.model.*;
import product.Query;

import java.util.List;

@Service
public class SearchReleaseService implements Query<String, SearchReleaseDTO> {

    private RestTemplate restTemplate;

    public SearchReleaseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable("searchAlbumCache")
    public ResponseEntity<SearchReleaseDTO> execute(String title){
        final String url = "https://musicbrainz.org/ws/2/release-group?query=";

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "MediaMenu/1.0 (yunuseshesh@gmail.com)");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<MBReleaseGroupResponse> response = restTemplate.exchange(
                url + title + "&fmt=json",
                HttpMethod.GET,
                entity,
                MBReleaseGroupResponse.class
        );

        List<MBReleaseDTO> releaseGroups = response.getBody().getReleaseGroups().stream()
                .map(album -> new MBReleaseDTO(
                        album.getTitle(),
                        album.getArtistCredit(),
                        album.getReleases().get(album.getReleases().toArray().length - 1).getId()))
                //return the LAST element in the releases list, b/c the list is sorted from latest release date to earliest
                //the first release has the most recognized metadata and especially image
                        .toList();

        SearchReleaseDTO searchReleaseDTO = new SearchReleaseDTO(releaseGroups);
        return ResponseEntity.ok(searchReleaseDTO);
    }

}
