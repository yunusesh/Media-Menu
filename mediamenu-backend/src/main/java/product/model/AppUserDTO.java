package product.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AppUserDTO {
    private Integer id;
    private String username;
    private String hashedPassword;
    private String email;
    private String profilePicture;
    private Timestamp createdAt;

    public AppUserDTO(AppUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.hashedPassword = user.getHashedPassword();
        this.email = user.getEmail();
        this.profilePicture = user.getProfilePicture();
        this.createdAt = user.getCreatedAt();
    }
}
