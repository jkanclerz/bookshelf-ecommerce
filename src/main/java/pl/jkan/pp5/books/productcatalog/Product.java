package pl.jkan.pp5.books.productcatalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jkan.pp5.books.productcatalog.dto.ProductData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue
    Long id;

    String name;
    String description;

    BigDecimal price;
    Boolean published;

    public ProductData toData() {
        return new ProductData(id, name, price);
    }
}
