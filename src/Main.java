package src;

import java.util.Scanner;
import src.controller.AuthController;
import src.controller.CredentialController;
import src.controller.PasswordGenerator;
import src.db.DatabaseHelper;

public class Main {
    public static void main(String[] args) {
        DatabaseHelper.initializeDatabase();
        Scanner scanner = new Scanner(System.in);
        AuthController authController = new AuthController(scanner);
        if (!authController.authenticate()) {
            System.out.println("Autenticação falhou. Encerrando...");
            return;
        }
        CredentialController credentialController = new CredentialController(scanner);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar credencial");
            System.out.println("2. Listar credenciais");
            System.out.println("3. Remover credencial");
            System.out.println("4. Gerar senha segura");
            System.out.println("5. Verificar vazamento de senha");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    credentialController.addCredential();
                    break;
                case "2":
                    credentialController.listCredentials();
                    break;
                case "3":
                    credentialController.removeCredential();
                    break;
                case "4":
                    System.out.println("Senha gerada: " + PasswordGenerator.generate(16));
                    break;
                case "5":
                    credentialController.checkBreach();
                    break;
                case "0":
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}