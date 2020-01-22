package pl.jkan.pp5.books.sales;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryProductCatalog implements ProductCatalog {

    private List<Product> availableProducts = new ArrayList<>();

    @Override
    public Optional<Product> load(Long productId) {
        return availableProducts.stream()
                .filter(product -> product.getId() == productId)
                .findFirst();
    }

    public void setAvailableProducts(List<Product> asList) {
        this.availableProducts = asList;
    }
}
