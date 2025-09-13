package product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//examples of controllers that are used to write and read data with HTTP requests
@RestController
public class ProductController {

    @PostMapping
    public ResponseEntity<String> createProduct() {
        return ResponseEntity.status(HttpStatus.OK).body("Product Created");
    }

    @GetMapping
    public ResponseEntity<String> getProduct() {
        return ResponseEntity.status(HttpStatus.OK).body("Got Product");
    }


    @PutMapping
    public ResponseEntity<String> updateProduct() {
        return ResponseEntity.status(HttpStatus.OK).body("Product Updated");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product Deleted");
    }


}
