package product.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.model.AppUser;
import product.model.AppUserDTO;
import product.ProductRepository;
import product.Query;

import java.util.Optional;

@Service
public class GetUserService implements Query<Integer, AppUserDTO> {

    private final ProductRepository productRepository;

    public GetUserService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<AppUserDTO> execute(Integer input) {
        //account for null value if database cant find it
        Optional<AppUser> userOptional = productRepository.findById(input);
        if(userOptional.isPresent()){
            return ResponseEntity.ok(new AppUserDTO(userOptional.get()));
        }

        return null;//in future add a cant find response
    }
}
