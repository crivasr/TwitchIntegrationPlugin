package tk.camikase.TwitchIntegration.utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {
    public static String hmacSha256(String key, String data) throws NoSuchAlgorithmException, InvalidKeyException {
        final Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        final SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

        sha256_HMAC.init(secret_key);

        final byte[] hashBytes = sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
        final StringBuilder sb = new StringBuilder();

        for (byte b : hashBytes) sb.append(String.format("%02x", b));

        return sb.toString();
    }

    public static String randomString(int length, String charset) {
        final SecureRandom random = new SecureRandom();
        final char[] buf = new char[length];

        for (int i = 0; i < length; i++) {
            final int index = random.nextInt(charset.length());
            buf[i] = charset.charAt(index);
        }

        return new String(buf);
    }
}
