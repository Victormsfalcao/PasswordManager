package src.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

public class BreachChecker {
    private static final String API_URL = "https://api.pwnedpasswords.com/range/";

    public static boolean isBreached(String password) {
        try {
            // Hash da senha em SHA-1
            String hash = sha1Hash(password);
            String prefix = hash.substring(0, 5);
            String suffix = hash.substring(5).toUpperCase();

            // Fazer a requisição à API
            HttpURLConnection connection = (HttpURLConnection) new URL(API_URL + prefix).openConnection();
            connection.setRequestMethod("GET");

            // Ler a resposta
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equalsIgnoreCase(suffix)) {
                    return true; // A senha foi encontrada em vazamentos
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // A senha não foi encontrada em vazamentos
    }

    private static String sha1Hash(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hash = digest.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString().toUpperCase();
    }
}
