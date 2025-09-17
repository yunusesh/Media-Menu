package product.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.Command;
import product.ProductRepository;
import product.model.Product;

import java.util.Optional;

@Service
public class DeleteProductService implements Command<Integer, Void> {
    private ProductRepository productRepository;

    public DeleteProductService(ProductRepository privateRespitory) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return null; //add exception later

    }
}
