package src;

import src.utils.BreachChecker;
import java.util.Scanner;

public class BreachCheckerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a senha para verificar vazamento: ");
        String password = scanner.nextLine();

        boolean breached = BreachChecker.isBreached(password);

        if (breached) {
            System.out.println("⚠️ Essa senha já apareceu em vazamentos!");
        } else {
            System.out.println("✅ Essa senha não foi encontrada em vazamentos.");
        }

        scanner.close();
    }
}
