package product;

import product.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.model.ProductDTO;
import product.model.UpdateProductCommand;
import product.services.*;

import java.util.List;

//examples of controllers that are used to write and read data with HTTP requests
@RestController
public class ProductController {
    private final CreateProductService createProductService;

    private final UpdateProductService updateProductService;

    private final GetProductsService getProductsService;

    private final DeleteProductService deleteProductService;

    private final GetProductService getProductService;


    public ProductController(CreateProductService createProductService,
                             UpdateProductService updateProductService,
                             GetProductsService getProductsService,
                             DeleteProductService deleteProductService,
                             GetProductService getProductService) {
        this.createProductService = createProductService;
        this.getProductsService = getProductsService;
        this.updateProductService = updateProductService;
        this.deleteProductService = deleteProductService;
        this.getProductService = getProductService;
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return createProductService.execute(product);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return getProductsService.execute(null);
    }

    @GetMapping("/products")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id){
        return getProductService.execute(id);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return updateProductService.execute(new UpdateProductCommand(id, product));
    }

    @DeleteMapping("/product/{id}") // id here must match id in line 64
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        return deleteProductService.execute(id);
    }


}
