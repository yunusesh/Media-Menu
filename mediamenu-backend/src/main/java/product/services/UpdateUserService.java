package product.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.Command;
import product.ProductRepository;
import product.model.AppUser;
import product.model.AppUserDTO;
import product.model.UpdateUserCommand;

import java.util.Optional;

@Service
public class UpdateUserService implements Command<UpdateUserCommand, AppUserDTO> {
    private ProductRepository productRepository;

    public UpdateUserService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<AppUserDTO> execute(UpdateUserCommand command) {
        Optional<AppUser> productOptional = productRepository.findById(command.getId());
        if (productOptional.isPresent()) {
            AppUser user = command.getUser();
            user.setId(command.getId());
            productRepository.save(user);
            return ResponseEntity.ok(new AppUserDTO(user));
        }
        return null; //exception later;
    }
}
