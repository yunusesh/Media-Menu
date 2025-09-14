package product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//examples of controllers that are used to write and read data with HTTP requests
@RestController
public class ProductController {
    private final PostProductService PostProductService;

    private final PutProductService PutProductService;

    private final GetProductService GetProductService;

    private final DeleteProductService DeleteProductService;


    public ProductController(PostProductService postProductService,
                             PutProductService putProductService,
                             GetProductService getProductService,
                             DeleteProductService deleteProductService) {
        PostProductService = postProductService;
        PutProductService = putProductService;
        GetProductService = getProductService;
        DeleteProductService = deleteProductService;
    }

    @PostMapping
    public ResponseEntity<String> createProduct() {
        return PostProductService.execute(null);
    }

    @GetMapping
    public ResponseEntity<String> getProduct() {
        return GetProductService.execute(null);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct() {
        return PutProductService.execute(null);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct() {
        return DeleteProductService.execute(null);
    }


}
