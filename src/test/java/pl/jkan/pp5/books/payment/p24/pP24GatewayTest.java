package pl.jkan.pp5.books.payment.p24;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class pP24GatewayTest {
    private static final String EXPECTED_MD5_CODE = "ee89f7848d91959e8f46803ea9476778";
    public static final String MERCHANT_ID = "123456";
    public static final String CRC_CODE = "crc_123456789";

    class SpyHttpClient implements HttpFormClient {
        public Map<String, String> lastRequest;
        @Override
        public String post(String url, Map<String, String> params) {
            lastRequest = params;
            return "OK";
        }
    }

    @Test void itGenerateRedirectionURLBasedOnToken() {
        SpyHttpClient client = new SpyHttpClient();
        P24Gateway p24Gateway = thereIsp24Gateway(client);
        String token = "my_token";
        String redirectUrl = p24Gateway.getPaymentUrl(token);

        assertThat(redirectUrl).isEqualTo("https://sandbox.przelewy24.pl/trnRequest/my_token");

    }

    @Test
    public void itAllowRegisterTransaction() {
        //Arrange
        PaymentRequest request = thereIsExamplePaymentRequest();
        SpyHttpClient client = new SpyHttpClient();
        P24Gateway p24Gateway = thereIsp24Gateway(client);

        String paymentToken = p24Gateway.registerPayment(request);

        assertThat(client.lastRequest).containsKeys("p24_merchant_id", "p24_pos_id", "p24_sign", "p24_api_version");
        assertThat(client.lastRequest.get("p24_sign")).isEqualTo(EXPECTED_MD5_CODE);
        assertThat(client.lastRequest).isEqualToComparingFieldByField(expectedRegisterParams());
    }

    private P24Gateway thereIsp24Gateway(SpyHttpClient client) {
        return new P24Gateway(
                P24Properties.of(MERCHANT_ID, CRC_CODE),
                    client,
                    new Md5Encoder());
    }

    private Map<String,String> expectedRegisterParams() {
        return new HashMap<>();
    }

    private PaymentRequest thereIsExamplePaymentRequest() {
        return PaymentRequest.builder()
                .email("john.doe@example.dev")
                .amount(BigDecimal.valueOf(25.50))
                .transactionId("order_1")
                .build();
    }
}
