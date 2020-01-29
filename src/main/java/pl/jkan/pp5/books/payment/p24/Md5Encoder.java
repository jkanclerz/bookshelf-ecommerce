package pl.jkan.pp5.books.payment.p24;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Encoder {
    public String encode(String input) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(input.getBytes(), 0, input.length());
        BigInteger i = new BigInteger(1,m.digest());
        return String.format("%1$032x", i);
    }
}
