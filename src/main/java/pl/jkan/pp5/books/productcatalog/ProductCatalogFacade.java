package pl.jkan.pp5.books.productcatalog;

import pl.jkan.pp5.books.productcatalog.dto.ProductData;
import pl.jkan.pp5.books.productcatalog.exceptions.NoSuchProductException;

import java.util.List;

public class ProductCatalogFacade {

    ProductRepository productRepository;

    public ProductCatalogFacade(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long create(Product product) {
        productRepository.save(product);

        return product.id;
    }

    public List<Product> all() {
        return productRepository.findAll();
    }

    public List<Product> allPublished() {
        return productRepository.findAllPublished();
    }

    public ProductData load(Long id) {
        ProductData product = productRepository.findById(id)
                .map(Product::toData)
                .orElseThrow(NoSuchProductException::new);

        return product;
    }
}
