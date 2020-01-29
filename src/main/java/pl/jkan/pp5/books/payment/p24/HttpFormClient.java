package pl.jkan.pp5.books.payment.p24;

import java.util.Map;

public interface HttpFormClient {
    String post(String url, Map<String, String> params);
}
