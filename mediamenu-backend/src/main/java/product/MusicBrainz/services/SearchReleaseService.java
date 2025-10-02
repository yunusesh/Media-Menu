package product.MusicBrainz.services;

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
    private String url = "https://musicbrainz.org/ws/2/release-group?query=";

    public SearchReleaseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<SearchReleaseDTO> execute(String title){
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
                        album.getReleases().get(validRelease(album, response)).getId()))
                        .toList();

        SearchReleaseDTO searchReleaseDTO = new SearchReleaseDTO(releaseGroups);
        return ResponseEntity.ok(searchReleaseDTO);
    }

    //these two functions work, but they are VERY slow
    //i should instead default to grabbing the first release, and changing it in the frontend if needed
    private boolean hasArt(String id, ResponseEntity<MBReleaseGroupResponse> response){
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "MediaMenu/1.0 (yunuseshesh@gmail.com)");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<MBReleaseResponse> imageResponse = restTemplate.exchange(
                "https://musicbrainz.org/ws/2/release/" + id + "?inc=recordings+artists&fmt=json",
                HttpMethod.GET,
                entity,
                MBReleaseResponse.class
        );

        return imageResponse.getBody().getCoverArtArchive().getCount() != 0;
    }

    private int validRelease(MBReleaseDTO album, ResponseEntity<MBReleaseGroupResponse> response){
        int i = 0;
        while(i < album.getReleases().size() - 1){

            System.out.println(album.getReleases().get(i).getId());

            if(hasArt(album.getReleases().get(i).getId(), response)) return i;
            else i++;
        }

        return i;
    }

}
