package pl.jkan.pp5.books.payment.p24;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    String email;
    BigDecimal amount;
    String transactionId;

    public BigInteger moneyAs100unit() {
        return amount.multiply(BigDecimal.valueOf(100)).toBigInteger();
    }
}
