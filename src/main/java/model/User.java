package model;

import java.util.UUID;

public class User {
    private final String email;
    private final String password;
    private final String name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public static User getRandomUser() {
        String randomString = UUID.randomUUID().toString().substring(0, 10);
        return new User(
                "test-" + randomString + "@example.com",
                "password-" + randomString,
                "user-" + randomString
        );
    }

}