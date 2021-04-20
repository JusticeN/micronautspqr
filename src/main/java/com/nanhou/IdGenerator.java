package com.nanhou;

import java.security.SecureRandom;
import java.util.Base64;

public class IdGenerator {
    private static final SecureRandom random = new SecureRandom();
    private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

    public static String generate() {
        byte[] buffer = new byte[8];
        random.nextBytes(buffer);
        return encoder.encodeToString(buffer).substring(0, 11);
    }
}
