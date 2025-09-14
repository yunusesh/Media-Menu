package product;

import org.springframework.http.ResponseEntity;

public interface Query <I,O>{ //Input, Output
    ResponseEntity<O> execute(I input);
}
