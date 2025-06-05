package src.security;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtils {
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 128;
    private static final int SALT_LENGTH = 16;
    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 256;

    private static SecretKey deriveKey(String masterKey, byte[] salt) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        PBEKeySpec spec = new PBEKeySpec(
            masterKey.toCharArray(),
            salt,
            ITERATION_COUNT,
            KEY_LENGTH
        );
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
    }

    public static String encrypt(String data) throws Exception {
        String masterKey = MasterKeyManager.getMasterKey();
        if (masterKey == null || masterKey.isEmpty()) {
            throw new IllegalStateException("Chave mestra não configurada!");
        }

        // Gerar salt e IV aleatórios
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        byte[] iv = new byte[GCM_IV_LENGTH];
        secureRandom.nextBytes(salt);
        secureRandom.nextBytes(iv);

        // Derivar a chave usando PBKDF2
        SecretKey key = deriveKey(masterKey, salt);

        // Configurar o cipher com AES-GCM
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);

        // Adicionar salt como dados adicionais autenticados
        cipher.updateAAD(salt);

        // Encriptar os dados
        byte[] cipherText = cipher.doFinal(data.getBytes());

        // Combinar salt, IV e texto cifrado
        byte[] message = new byte[salt.length + iv.length + cipherText.length];
        System.arraycopy(salt, 0, message, 0, salt.length);
        System.arraycopy(iv, 0, message, salt.length, iv.length);
        System.arraycopy(cipherText, 0, message, salt.length + iv.length, cipherText.length);

        return Base64.getEncoder().encodeToString(message);
    }

    public static String decrypt(String encryptedData) throws Exception {
        String masterKey = MasterKeyManager.getMasterKey();
        if (masterKey == null || masterKey.isEmpty()) {
            throw new IllegalStateException("Chave mestra não configurada!");
        }

        // Decodificar a mensagem completa
        byte[] message = Base64.getDecoder().decode(encryptedData);

        // Extrair salt, IV e texto cifrado
        byte[] salt = new byte[SALT_LENGTH];
        byte[] iv = new byte[GCM_IV_LENGTH];
        byte[] cipherText = new byte[message.length - SALT_LENGTH - GCM_IV_LENGTH];

        System.arraycopy(message, 0, salt, 0, SALT_LENGTH);
        System.arraycopy(message, SALT_LENGTH, iv, 0, GCM_IV_LENGTH);
        System.arraycopy(message, SALT_LENGTH + GCM_IV_LENGTH, cipherText, 0, cipherText.length);

        // Derivar a chave usando PBKDF2
        SecretKey key = deriveKey(masterKey, salt);

        // Configurar o cipher para decriptação
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);

        // Adicionar salt como dados adicionais autenticados
        cipher.updateAAD(salt);

        // Decriptar os dados
        byte[] decryptedData = cipher.doFinal(cipherText);
        return new String(decryptedData);
    }
}