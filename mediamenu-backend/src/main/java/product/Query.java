package product;

import org.springframework.http.ResponseEntity;

public interface Query <I,O>{ //Input, Output
    ResponseEntity<O> execute(I input);
}
//interface for service classes, follow the format of execute(input) returning ResponseEntity<Output>