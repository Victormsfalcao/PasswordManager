package src;

import controller.AuthController;
import controller.CredentialController;
import controller.PasswordGenerator;
import db.DatabaseHelper;

import java.util.Scanner;

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
            System.out.println("3. Gerar senha segura");
            System.out.println("4. Verificar vazamento de senha");
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
                    System.out.println("Senha gerada: " + PasswordGenerator.generate(16));
                    break;
                case "4":
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