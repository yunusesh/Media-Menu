package product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PutProductService {
    public ResponseEntity<String> execute(){
        return ResponseEntity.status(HttpStatus.OK).body("Product Updated");

    }

}
