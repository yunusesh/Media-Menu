package product.MusicBrainz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//data is getters and setters
@Data
@NoArgsConstructor
public class MBArtistDTO {
    private String name;
    private String id;

    public MBArtistDTO(String name){
        this.name = name;
    }

    public MBArtistDTO(String name, String id){
        this.name = name;
        this.id = id;
    }
}
