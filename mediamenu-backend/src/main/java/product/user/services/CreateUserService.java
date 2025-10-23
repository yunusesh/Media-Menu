package product.user.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.Command;
import product.user.UserRepository;
import product.user.model.AppUser;
import product.user.model.AppUserDTO;

@Service
public class CreateUserService implements Command<AppUser, AppUserDTO> {

    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<AppUserDTO> execute(AppUser user) {
        AppUser savedUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new AppUserDTO(savedUser));
    }

}