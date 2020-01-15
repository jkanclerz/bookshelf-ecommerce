package pl.jkan.pp5.books.productcatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CatalogController {

    @Autowired
    private ProductCatalogFacade productCatalogFacade;

    @GetMapping("/products")
    public List<Product> products() {
        return productCatalogFacade.allPublished();
    }
}
