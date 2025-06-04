package src.security;

import org.apache.commons.codec.binary.Base32;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.time.Instant;

public class TwoFactorAuth {
    private static final int TIME_STEP_SECONDS = 30;
    private static final int CODE_DIGITS = 6;
    private static final String HMAC_ALGORITHM = "HmacSHA1";

    public static boolean verifyCode(String base32Secret, String code) {
        long currentInterval = Instant.now().getEpochSecond() / TIME_STEP_SECONDS;
        try {
            for (int i = -1; i <= 1; i++) { // Checa intervalo anterior, atual e próximo para tolerância
                String generatedCode = generateCode(base32Secret, currentInterval + i);
                if (generatedCode.equals(code)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String generateCode(String base32Secret, long interval) throws Exception {
        Base32 base32 = new Base32();
        byte[] key = base32.decode(base32Secret);
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(interval);
        byte[] timeBytes = buffer.array();

        Mac mac = Mac.getInstance(HMAC_ALGORITHM);
        mac.init(new SecretKeySpec(key, HMAC_ALGORITHM));
        byte[] hash = mac.doFinal(timeBytes);

        int offset = hash[hash.length - 1] & 0xF;
        int binary =
                ((hash[offset] & 0x7F) << 24) |
                ((hash[offset + 1] & 0xFF) << 16) |
                ((hash[offset + 2] & 0xFF) << 8) |
                (hash[offset + 3] & 0xFF);

        int otp = binary % (int) Math.pow(10, CODE_DIGITS);
        return String.format("%0" + CODE_DIGITS + "d", otp);
    }
}