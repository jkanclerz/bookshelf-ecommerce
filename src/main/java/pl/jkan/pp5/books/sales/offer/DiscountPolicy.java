package pl.jkan.pp5.books.sales.offer;

public interface DiscountPolicy {
    public Discount calculateDiscount(OfferItem item);
}
