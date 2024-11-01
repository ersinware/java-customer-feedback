package repository;

import java.util.HashMap;

import constants.Constants;
import repository.types.User;

public class UserRepository {
    private static final HashMap<String, User> users = new HashMap<>() {
        {
            put("ekaraer", new User(
                    "ekaraer",
                    "pass",
                    "Ersin",
                    "Karaer",
                    true));

            put("user", new User(
                    "user",
                    "pass",
                    "Ersin",
                    "Karaer",
                    false));
        }
    };

    protected static boolean addNewUser(
            String username,
            String password,
            String name,
            String surname) {
        if (users.containsKey(username)) {
            System.out.println("\nAn user exists with the same username");

            return false;
        }

        users.put(username, new User(username, password, name, surname, false));

        return true;
    }

    protected static String[] getPasswordAndAuthorization(String username) {
        User user = users.getOrDefault(username, null);
        if (user == null)
            return null;

        return new String[] {
                user.getPassword(),
                user.isAdmin() ? Constants.AUTHENTICATED_ADMIN : Constants.AUTHENTICATED_USER
        };
    }
}
