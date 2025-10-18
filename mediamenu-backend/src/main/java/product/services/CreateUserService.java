package product.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.Command;
import product.ProductRepository;
import product.model.AppUser;
import product.model.AppUserDTO;

@Service
public class CreateUserService implements Command<AppUser, AppUserDTO> {

    private final ProductRepository productRepository;

    public CreateUserService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<AppUserDTO> execute(AppUser user) {
        AppUser savedUser = productRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new AppUserDTO(savedUser));
    }
}