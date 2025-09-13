package product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//examples of controllers that are used to write and read data with HTTP requests
@RestController
public class ProductController {
    @Autowired
    private PostProductService PostProductService;

    @Autowired
    private PutProductService PutProductService;

    @Autowired
    private GetProductService GetProductService;

    @Autowired
    private DeleteProductService DeleteProductService;


    @PostMapping
    public ResponseEntity<String> createProduct() {
        return PostProductService.execute();
    }

    @GetMapping
    public ResponseEntity<String> getProduct() {
        return GetProductService.execute();
    }


    @PutMapping
    public ResponseEntity<String> updateProduct() {
        return PutProductService.execute();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct() {
        return DeleteProductService.execute();
    }


}
