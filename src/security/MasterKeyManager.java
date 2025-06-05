package src.security;

import java.util.Scanner;

public class MasterKeyManager {
    private static String masterKey = null;

    public static String getMasterKey() {
        if (masterKey != null) {
            return masterKey;
        }

        // Tenta primeiro pegar da variável de ambiente
        masterKey = System.getenv("PASSWORD_MANAGER_KEY");
        if (masterKey != null) {
            return masterKey;
        }

        // Se não encontrou, pede para o usuário configurar
        System.out.println("\n=== Configuração da Chave Mestra ===");
        System.out.println("A chave mestra é necessária para proteger suas senhas.");
        System.out.println("Esta chave será usada para criptografar todas as suas senhas.");
        System.out.println("IMPORTANTE: Guarde esta chave em um lugar seguro!");
        System.out.println("Se você perdê-la, não será possível recuperar suas senhas.");
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("\nDigite sua chave mestra (mínimo 12 caracteres): ");
            String key = scanner.nextLine().trim();
            
            if (key.length() < 12) {
                System.out.println("❌ A chave precisa ter pelo menos 12 caracteres!");
                continue;
            }
            
            System.out.print("Digite novamente para confirmar: ");
            String confirmKey = scanner.nextLine().trim();
            
            if (!key.equals(confirmKey)) {
                System.out.println("❌ As chaves não coincidem! Tente novamente.");
                continue;
            }

            // Verifica força da senha
            if (!isStrongKey(key)) {
                System.out.println("⚠️  Recomendação: Use uma combinação de letras maiúsculas, minúsculas,");
                System.out.println("   números e símbolos para uma chave mais segura.");
                System.out.print("Deseja usar esta chave mesmo assim? (s/n): ");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (!choice.equals("s")) {
                    continue;
                }
            }
            
            masterKey = key;
            System.out.println("\n✅ Chave mestra configurada com sucesso!");
            
            System.out.println("\nDica: Para não precisar digitar a chave toda vez,");
            System.out.println("configure a variável de ambiente PASSWORD_MANAGER_KEY");
            System.out.println("com o valor da sua chave mestra.");
            
            return masterKey;
        }
    }

    private static boolean isStrongKey(String key) {
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : key.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }

        return hasUpper && hasLower && hasDigit && hasSpecial;
    }
} 