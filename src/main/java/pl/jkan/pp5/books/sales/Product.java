package pl.jkan.pp5.books.sales;

import java.math.BigDecimal;

public class Product {
    private Long productId;
    private BigDecimal price;
    private Integer quantity;

    public Product(Long productId, BigDecimal price) {
        this.productId = productId;
        this.price = price;
        this.quantity = 1;
    }

    public long getId() {
        return productId;
    }


}
