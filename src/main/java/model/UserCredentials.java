package model;

public class UserCredentials {
    private final String email;
    private final String password;

    public UserCredentials(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
