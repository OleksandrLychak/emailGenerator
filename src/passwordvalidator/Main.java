package passwordvalidator;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean continueChecking = true;

        Scanner scanner = new Scanner(System.in);

        while (continueChecking) {
            System.out.println("Enter your password");
            String password = scanner.nextLine();

            List<ValidationError> errors = PasswordValidator.validatePassword(password);

            if (errors.isEmpty()) {
                System.out.println("Password is valid");
            } else {
                System.out.println("Password is invalid");
                for (ValidationError error : errors) {
                    System.out.println("- " + error.getMessage());
                }
            }

            System.out.println("Check another password? (y/n)");
            String answer = scanner.nextLine();

            continueChecking = answer.equalsIgnoreCase("y");
        }

        scanner.close();

    }
}
