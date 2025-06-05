package src.controller;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Scanner;
import java.util.prefs.Preferences;
import src.security.TwoFactorAuth;

public class AuthController {
    private Scanner scanner;
    private static final String MASTER_HASH_KEY = "master_password_hash";
    private Preferences prefs;

    public AuthController(Scanner scanner) {
        this.scanner = scanner;
        this.prefs = Preferences.userNodeForPackage(AuthController.class);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isMasterPasswordSet() {
        return prefs.get(MASTER_HASH_KEY, null) != null;
    }

    private void setMasterPassword() {
        while (true) {
            System.out.println("\n=== Configuração da Senha Mestra ===");
            System.out.println("Esta senha será necessária para acessar e revelar suas senhas.");
            System.out.print("Digite a nova senha mestra: ");
            String password = scanner.nextLine();

            if (password.length() < 8) {
                System.out.println("❌ A senha deve ter pelo menos 8 caracteres!");
                continue;
            }

            System.out.print("Confirme a senha mestra: ");
            String confirm = scanner.nextLine();

            if (!password.equals(confirm)) {
                System.out.println("❌ As senhas não coincidem!");
                continue;
            }

            String hashedPassword = hashPassword(password);
            prefs.put(MASTER_HASH_KEY, hashedPassword);
            System.out.println("✅ Senha mestra configurada com sucesso!");
            break;
        }
    }

    public String getMasterPassword() {
        if (!isMasterPasswordSet()) {
            setMasterPassword();
        }

        System.out.print("Digite a senha mestra: ");
        String password = scanner.nextLine();
        String hashedInput = hashPassword(password);
        String storedHash = prefs.get(MASTER_HASH_KEY, null);

        if (storedHash != null && storedHash.equals(hashedInput)) {
            return password;
        }
        return null;
    }

    public boolean authenticate() {
        String masterPassword = getMasterPassword();
        if (masterPassword == null) {
            System.out.println("Senha mestra incorreta.");
            return false;
        }

        System.out.print("Digite o código 2FA do Google Authenticator: ");
        String code = scanner.nextLine();

        if (!TwoFactorAuth.verifyCode("JBSWY3DPEHPK3PXP", code)) {
            System.out.println("Código 2FA inválido.");
            return false;
        }
        System.out.println("Autenticado com sucesso!");
        return true;
    }
}