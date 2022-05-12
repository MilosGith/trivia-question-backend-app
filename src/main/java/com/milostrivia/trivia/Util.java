package com.milostrivia.trivia;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Util {

    private Util() {}

    public static String decodeBase64(String encodedText) {
        byte[] decoded = Base64.getDecoder().decode(encodedText);
        return new String(decoded, StandardCharsets.UTF_8);
    }
}
