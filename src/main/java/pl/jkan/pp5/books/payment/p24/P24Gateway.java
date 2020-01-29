package pl.jkan.pp5.books.payment.p24;

import org.springframework.http.RequestEntity;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class P24Gateway {
    public static final String HARDCODED_CURRENCY = "PLN";
    private P24Properties properties;
    private HttpFormClient http;
    private Md5Encoder encoder;

    public P24Gateway(P24Properties properties, HttpFormClient client, Md5Encoder encoder) {
        this.properties = properties;
        this.http = client;
        this.encoder = encoder;
    }

    public String registerPayment(PaymentRequest request) {
        Map<String, String> paramsToSend = new HashMap<>();

        paramsToSend.put("p24_merchant_id", properties.merchantId);
        paramsToSend.put("p24_pos_id", properties.merchantId);
        paramsToSend.put("p24_session_id", request.transactionId);
        paramsToSend.put("p24_api_version", "3.2");
        paramsToSend.put("p24_sign", generateMd5Checksum(request));
        paramsToSend.put("p24_amount", request.moneyAs100unit().toString());
        paramsToSend.put("p24_description", "payment for ebooks");
        paramsToSend.put("p24_country", "PL");

        String response = sendHttpRequest(properties.getRegisterUrl(), paramsToSend);

        return response;
    }



    private String generateMd5Checksum(PaymentRequest request) {
        try {
            return encoder.encode(String.format(
                    "%s|%s|%s|%s|%s",
                    request.transactionId,
                    properties.merchantId,
                    request.moneyAs100unit(),
                    HARDCODED_CURRENCY,
                    properties.crcCode
            ));
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    private String sendHttpRequest(String url, Map<String, String> params) {
        return this.http.post(url, params);
    }

    private BigInteger moneyAs100unit(PaymentRequest request) {
        return request.amount.toBigInteger().multiply(BigInteger.valueOf(100));
    }
}
