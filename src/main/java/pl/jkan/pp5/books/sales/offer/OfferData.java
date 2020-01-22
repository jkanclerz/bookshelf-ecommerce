package pl.jkan.pp5.books.sales.offer;

public class OfferData {
    private Double total;
    private Integer itemsCount;
    private String currency = "PLN";

    public OfferData(Double total, Integer itemsCount) {
        this.total = total;
        this.itemsCount = itemsCount;
    }

    public Double getTotal() {
        return total;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    public String getCurrency() {
        return currency;
    }
}
