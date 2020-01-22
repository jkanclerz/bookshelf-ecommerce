package pl.jkan.pp5.books.sales.offer.discounts;


import pl.jkan.pp5.books.sales.offer.Discount;
import pl.jkan.pp5.books.sales.offer.DiscountPolicy;
import pl.jkan.pp5.books.sales.offer.OfferItem;

public class QuantityDiscount implements DiscountPolicy {

    private Integer itemCount;
    private Double discountValue;

    public QuantityDiscount(Integer itemCount, Double discountValue) {
        this.itemCount = itemCount;
        this.discountValue = discountValue;
    }

    @Override
    public Discount calculateDiscount(OfferItem item) {
        if (item.getQuantity() >= itemCount) {
            return new Discount("Item count", (item.getTotalCost() * discountValue));
        }

        return Discount.noDiscount();
    }
}
