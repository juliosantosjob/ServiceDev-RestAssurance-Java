package automation.dev.serverest.api.entities;

public class LoginEntities {
    private String email;
    private String password;

    // Construtor padrão
    public LoginEntities() {
    }

    // Construtor parametrizado
    public LoginEntities(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}