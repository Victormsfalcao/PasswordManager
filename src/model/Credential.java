package src.model;

public class Credential {
    private int id;
    private String service;
    private String username;
    private String password;

    public Credential(int id, String service, String username, String password) {
        this.id = id;
        this.service = service;
        this.username = username;
        this.password = password;
    }

    // Getters e setters
    public int getId() { return id; }
    public String getService() { return service; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public void setId(int id) { this.id = id; }
    public void setService(String service) { this.service = service; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
}