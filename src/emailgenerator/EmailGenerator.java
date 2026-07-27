package emailgenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Generates a unique email address for testing purposes
 * by appending a timestamp suffix to a base email using
 * plus-addressing (base+suffix@domain).
 *
 * Example output: neville+27072026_143520@sharkscode.com
 */

public class EmailGenerator {

    private static final String BASE_EMAIL = "neville";
    private static final String DOMAIN = "@sharkscode.com";
    private static final String TIMESTAMP_PATTERN = "ddMMyyyy_HHmmss";

    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN);
        String timestamp = now.format(formatter);

        String email = BASE_EMAIL + "+" + timestamp + DOMAIN;

        System.out.println("Generated email: " + email);
    }
}