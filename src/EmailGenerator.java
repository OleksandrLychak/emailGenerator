import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmailGenerator {

    static String baseEmail = "neville";
    static String domain = "@sharkscode.com";

    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyMMdd_HHmmss");
        String timestamp = now.format(formatter);

        String email = baseEmail + "+" + timestamp + domain;

        System.out.println("Згенерований email: " + email);
    }
}