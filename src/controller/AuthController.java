package src.controller;
import src.security.TwoFactorAuth;
import java.util.Scanner;

public class AuthController {
    private Scanner scanner;
    private static final String MASTER_PASSWORD = "admin123"; // Defina a senha mestra aqui

    public AuthController(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean authenticate() {
        System.out.print("Digite a senha mestre: ");
        String masterPassword = scanner.nextLine();

        if (!MASTER_PASSWORD.equals(masterPassword)) {
            System.out.println("Senha mestra incorreta.");
            return false;
        }

        System.out.print("Digite o código 2FA do Google Authenticator: ");
        String code = scanner.nextLine();

        if (!TwoFactorAuth.verifyCode("JBSWY3DPEHPK3PXP", code)) { // Exemplo de chave
            System.out.println("Código 2FA inválido.");
            return false;
        }
        System.out.println("Autenticado com sucesso!");
        return true;
    }
}