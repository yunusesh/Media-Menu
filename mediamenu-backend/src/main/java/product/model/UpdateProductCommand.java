package product.model;

import lombok.Getter;

@Getter
public class UpdateProductCommand {
    private Integer id;
    private Product product;

    public UpdateProductCommand(Integer id, Product product) {
        this.product = product;
        this.id = id;
    }
}
