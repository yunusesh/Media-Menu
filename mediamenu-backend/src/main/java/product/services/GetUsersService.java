package product.services;

import product.model.AppUser;
import product.model.AppUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.ProductRepository;
import product.Query;

import java.util.List;

@Service
public class GetUsersService implements Query<Void, List<AppUserDTO>> {

    private final ProductRepository productRepository;

    public GetUsersService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override

    public ResponseEntity<List<AppUserDTO>> execute(Void input) {
        List<AppUser> users = productRepository.findAll();
        List<AppUserDTO> appUserDTOS = users.stream()
                .map(AppUserDTO::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(appUserDTOS);
    }

    }
