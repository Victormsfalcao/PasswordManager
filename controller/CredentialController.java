package controller;

import db.DatabaseHelper;
import security.EncryptionUtils;
import utils.BreachChecker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CredentialController {
    private Scanner scanner;

    public CredentialController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void addCredential() {
        try {
            System.out.print("Serviço: ");
            String service = scanner.nextLine();

            System.out.print("Usuário: ");
            String username = scanner.nextLine();

            System.out.print("Senha: ");
            String password = scanner.nextLine();

            String encryptedPassword = EncryptionUtils.encrypt(password);

            try (Connection conn = DatabaseHelper.getConnection()) {
                String sql = "INSERT INTO credentials(service, username, password) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, service);
                stmt.setString(2, username);
                stmt.setString(3, encryptedPassword);
                stmt.executeUpdate();
                System.out.println("Credencial adicionada com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("Erro ao adicionar credencial: " + e.getMessage());
        }
    }

    public void listCredentials() {
        try (Connection conn = DatabaseHelper.getConnection()) {
            String sql = "SELECT service, username, password FROM credentials";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                String decrypted = EncryptionUtils.decrypt(rs.getString("password"));
                System.out.printf("Serviço: %s | Usuário: %s | Senha: %s\n",
                        rs.getString("service"), rs.getString("username"), decrypted);
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar credenciais: " + e.getMessage());
        }
    }

    public void checkBreach() {
        System.out.print("Digite a senha para verificar vazamento: ");
        String password = scanner.nextLine();
        boolean breached = BreachChecker.isBreached(password);
        if (breached) {
            System.out.println("⚠  Essa senha já apareceu em vazamentos!");
        } else {
            System.out.println("✅ Essa senha não foi encontrada em vazamentos.");
        }
    }

}