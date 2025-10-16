package product.MusicBrainz.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import product.MusicBrainz.model.MBAlbumDTO;
import product.MusicBrainz.model.MBAlbumResponse;
import product.MusicBrainz.model.MBArtistDTO;
import product.MusicBrainz.model.MBTrackDTO;
import product.Query;

import java.util.List;

@Service

public class MBReissueService implements Query<String, MBAlbumDTO> {

    private RestTemplate restTemplate;

    public MBReissueService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<MBAlbumDTO> execute (String id){
        final String url = "https://musicbrainz.org/ws/2/release/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "MediaMenu/1.0 (yunuseshesh@gmail.com)");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<MBAlbumResponse> response = restTemplate.exchange(
                url + id + "?inc=recordings+artists+release-groups&fmt=json",
                HttpMethod.GET,
                entity,
                MBAlbumResponse.class
        );

        List<MBTrackDTO> tracklist = response.getBody().getMedia().stream()
                .flatMap(media -> media.getTracks().stream())
                .toList();

        List<MBArtistDTO> artists = response.getBody().getArtistCredit().stream()
                .map(artist -> new MBArtistDTO(artist.getArtist().getName(), artist.getArtist().getId()))
                .toList();

        MBAlbumDTO releaseGroup = new MBAlbumDTO(response.getBody().getReleaseGroup().getId());


        MBAlbumDTO mbAlbumDTO = new MBAlbumDTO(
                response.getBody().getTitle(),
                response.getBody().getId(),
                response.getBody().getDate(),
                response.getBody().getPrimaryType(),
                response.getBody().getSecondaryTypes(),
                artists,
                tracklist,
                releaseGroup
        );

        return ResponseEntity.ok(mbAlbumDTO);
    }


}
