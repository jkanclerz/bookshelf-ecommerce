package pl.jkan.pp5.books.payment.p24;

public class P24Properties {
    public static final String SANDBOX_REGISTER_URL = "https://sandbox.przelewy24.pl/trnDirect";
    String merchantId;
    String crcCode;

    public P24Properties(String merchantId, String crcCode) {
        this.merchantId = merchantId;
        this.crcCode = crcCode;
    }

    public static P24Properties of(String merchantId, String crcCode) {
        return new P24Properties(merchantId, crcCode);
    }

    public String getRegisterUrl() {
        return SANDBOX_REGISTER_URL;
    }
}
