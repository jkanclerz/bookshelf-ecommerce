package pl.jkan.pp5.books.sales;


import java.util.Optional;

public interface ProductCatalog {
    Optional<Product> load(Long productId);
}
