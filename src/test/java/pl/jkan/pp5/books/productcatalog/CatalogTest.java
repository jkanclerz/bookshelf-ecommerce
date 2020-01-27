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
import pl.jkan.pp5.books.productcatalog.dto.ProductData;
import pl.jkan.pp5.books.productcatalog.exceptions.NoSuchProductException;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CatalogTest {
    public static final String PRODUCT_NAME = "my product";
    public static final long NOT_EXISTING_ID = 123456L;

    @Autowired
    ProductCatalogFacade producCatalogFacade;

    @Autowired
    ProductRepository productRepository;

    @Before
    public void setUp() {
        productRepository.deleteAll();
    }

    @Test
    public void exceptionWhenNotExistingProductIsLoaded() {
        assertThatThrownBy(() -> {
            producCatalogFacade.load(NOT_EXISTING_ID);
        }).isInstanceOf(NoSuchProductException.class);
    }

    @Test
    public void itAllowLoadSingleProductData() {
        Product product = thereIsPublishedProduct();

        Long id = producCatalogFacade.create(product);

        ProductData loaded = producCatalogFacade.load(id);

        assertThat(loaded.getId()).isEqualTo(id);
    }

    private Product thereIsPublishedProduct() {
        return Product.builder()
                    .name(PRODUCT_NAME)
                    .description("nice one")
                    .price(BigDecimal.valueOf(10.10))
                    .published(true)
                    .build();
    }
}
