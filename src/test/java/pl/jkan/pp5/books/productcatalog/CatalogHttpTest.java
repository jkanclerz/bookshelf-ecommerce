package pl.jkan.pp5.books.productcatalog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CatalogHttpTest {
    public static final String PRODUCT_NAME = "my product";
    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ProductCatalogFacade producCatalogFacade;

    @Autowired
    ProductRepository productRepository;

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        productRepository.deleteAll();
    }

    @Test
    public void itAllowCreateProducts() {
        Product product = thereIsPublishedProduct();

        producCatalogFacade.create(product);

        ResponseEntity<Product[]> response = restTemplate.getForEntity(getUrl("/api/products"), Product[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(Arrays.stream(response.getBody())
                .map(Product::getName))
                .contains(PRODUCT_NAME);
    }

    @Test
    public void itDoesNotListNotPublishedProducts() {
        Product product = thereIsNotPublishedProduct();

        producCatalogFacade.create(product);

        ResponseEntity<Product[]> response = restTemplate.getForEntity(getUrl("/api/products"), Product[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(0);
    }

    private Product thereIsNotPublishedProduct() {
        Product product = thereIsPublishedProduct();
        product.setPublished(false);

        return product;
    }

    private Product thereIsPublishedProduct() {
        return Product.builder()
                    .name(PRODUCT_NAME)
                    .description("nice one")
                    .price(BigDecimal.valueOf(10.10))
                    .published(true)
                    .build();
    }

    private String getUrl(String uri) {
        return String.format("http://localhost:%s/%s", port, uri);
    }
}
