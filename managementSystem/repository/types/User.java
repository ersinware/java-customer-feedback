package repository.types;

import util.Util;

public class User {
    private final String username;
    private final String password;
    private final String name;
    private final String surname;
    private final boolean admin;

    public User(String username, String password, String name, String surname, boolean admin) {
        if (Util.isNullExist(username, password, name, surname, admin))
            throw new RuntimeException("All parameters must be provided");

        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isAdmin() {
        return admin;
    }
}
