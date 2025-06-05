package src.security;

import org.apache.commons.codec.binary.Base32;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TwoFactorSetup {
    private static final String SECRET_KEY = "JBSWY3DPEHPK3PXP"; // Chave fixa para exemplo
    
    public static void showSetupInstructions() {
        try {
            String appName = URLEncoder.encode("Password Manager", StandardCharsets.UTF_8);
            String issuer = URLEncoder.encode("Secure Password Manager", StandardCharsets.UTF_8);
            
            System.out.println("\n=== Configuração do Google Authenticator ===");
            System.out.println("1. Abra o Google Authenticator no seu celular");
            System.out.println("2. Clique no + (adicionar)");
            System.out.println("3. Escolha 'Inserir chave de configuração'");
            System.out.println("4. Use os seguintes dados:");
            System.out.println("\n   Nome da conta: Password Manager");
            System.out.println("   Chave: " + SECRET_KEY);
            System.out.println("   Tipo: Baseado no tempo");
            System.out.println("\nOu use este link para gerar o QR code:");
            System.out.println("https://chart.googleapis.com/chart?cht=qr&chs=200x200&chl=" +
                    "otpauth://totp/" + appName + "?secret=" + SECRET_KEY + "&issuer=" + issuer);
            
            System.out.println("\n⚠️  IMPORTANTE: Guarde o Google Authenticator configurado em um lugar seguro!");
            System.out.println("   Você precisará dele para acessar suas senhas.\n");
            
            // Aguarda um pouco para o usuário ler as instruções
            Thread.sleep(2000);
            
            System.out.println("Agora vamos verificar se o 2FA foi configurado corretamente.");
            System.out.println("Abra o Google Authenticator e digite o código mostrado:");
            
        } catch (Exception e) {
            System.err.println("Erro ao gerar instruções 2FA: " + e.getMessage());
        }
    }
    
    public static String getSecretKey() {
        return SECRET_KEY;
    }
} 