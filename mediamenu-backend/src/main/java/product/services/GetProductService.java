package product.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product.model.ProductDTO;
import product.ProductRepository;
import product.Query;
import product.model.Product;

import java.util.Optional;

@Service
public class GetProductService implements Query<Integer, ProductDTO> {

    private final ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Integer input) {
        //account for null value if database cant find it
        Optional<Product> productOptional = productRepository.findById(input);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(new ProductDTO(productOptional.get()));
        }

        return null;//in future add a cant find response
    }
}
