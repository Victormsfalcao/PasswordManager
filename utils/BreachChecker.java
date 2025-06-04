package utils;

public class BreachChecker {
    public static boolean isBreached(String password) {
        // Dummy check para exemplo, deveria usar API real
        return password.equalsIgnoreCase("123456") || password.equalsIgnoreCase("password");
    }
}