package pl.jkan.pp5.books.sales;

import java.util.Optional;

public interface BasketStorage {
    Optional<Basket> loadForUser(String currentClientId);

    void save(Basket basket);
}
