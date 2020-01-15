package pl.jkan.pp5.books.productcatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductData {
    Long id;
    String name;
    BigDecimal price;
}
