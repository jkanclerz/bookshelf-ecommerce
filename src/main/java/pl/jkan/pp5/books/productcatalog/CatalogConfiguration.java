package pl.jkan.pp5.books.productcatalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogConfiguration {
    @Bean
    ProductCatalogFacade productCatalogFacade(ProductRepository productRepository) {
        return new ProductCatalogFacade(productRepository);
    }
}
