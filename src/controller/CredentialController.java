package src.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import src.db.DatabaseHelper;
import src.security.EncryptionUtils;
import src.security.TwoFactorAuth;
import src.utils.BreachChecker;

public class CredentialController {
    private Scanner scanner;
    private AuthController authController;

    public CredentialController(Scanner scanner) {
        this.scanner = scanner;
        this.authController = new AuthController(scanner);
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
            String sql = "SELECT id, service, username, password FROM credentials";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            System.out.println("\n=== Lista de Credenciais ===");
            while (rs.next()) {
                int id = rs.getInt("id");
                String service = rs.getString("service");
                String username = rs.getString("username");
                String encryptedPassword = rs.getString("password");
                
                System.out.printf("\nID: %d\nServiço: %s\nUsuário: %s\nSenha: %s\n",
                        id, service, username, encryptedPassword);
                System.out.println("----------------------------------------");
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar credenciais: " + e.getMessage());
        }
    }

    public void removeCredential() {
        try {
            listCredentials(); // Mostra a lista atual de credenciais
            
            System.out.print("\nDigite o ID da credencial que deseja remover (0 para cancelar): ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            if (id == 0) {
                System.out.println("Operação cancelada.");
                return;
            }

            // Verifica se a credencial existe
            try (Connection conn = DatabaseHelper.getConnection()) {
                String checkSql = "SELECT service, username FROM credentials WHERE id = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setInt(1, id);
                ResultSet rs = checkStmt.executeQuery();

                if (!rs.next()) {
                    System.out.println("❌ Credencial não encontrada!");
                    return;
                }

                // Mostra os detalhes da credencial que será removida
                String service = rs.getString("service");
                String username = rs.getString("username");
                System.out.printf("\nVocê está prestes a remover:\nServiço: %s\nUsuário: %s\n", 
                    service, username);
                
                System.out.print("\nTem certeza que deseja remover esta credencial? (s/n): ");
                String confirm = scanner.nextLine().trim().toLowerCase();
                
                if (!confirm.equals("s")) {
                    System.out.println("Operação cancelada.");
                    return;
                }

                // Solicita autenticação
                System.out.println("\n=== Autenticação Necessária ===");
                String masterPassword = authController.getMasterPassword();
                if (masterPassword == null) {
                    System.out.println("❌ Senha mestra incorreta! Operação cancelada.");
                    return;
                }

                System.out.print("Digite o código 2FA do Google Authenticator: ");
                String code = scanner.nextLine();
                if (!TwoFactorAuth.verifyCode("JBSWY3DPEHPK3PXP", code)) {
                    System.out.println("❌ Código 2FA inválido! Operação cancelada.");
                    return;
                }

                // Remove a credencial
                String deleteSql = "DELETE FROM credentials WHERE id = ?";
                PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
                deleteStmt.setInt(1, id);
                int rowsAffected = deleteStmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("✅ Credencial removida com sucesso!");
                } else {
                    System.out.println("❌ Erro ao remover credencial.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido! Digite um número.");
        } catch (Exception e) {
            System.err.println("Erro ao remover credencial: " + e.getMessage());
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