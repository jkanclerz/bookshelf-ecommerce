package pl.jkan.pp5.books.productcatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductData {
    Long id;
    String name;
    BigDecimal price;

    public ProductData(Long id, String name, BigDecimal price) {

        this.id = id;
        this.name = name;
        this.price = price;
    }
}
