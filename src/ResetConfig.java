package src;

import java.util.prefs.Preferences;
import src.controller.AuthController;

public class ResetConfig {
    public static void main(String[] args) {
        Preferences prefs = Preferences.userNodeForPackage(AuthController.class);
        prefs.remove("master_password_hash");
        System.out.println("✅ Configurações resetadas com sucesso!");
    }
} 