package pl.jkan.pp5.books.productcatalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("Select p from Product p WHERE p.published = 1")
    List<Product> findAllPublished();
}
