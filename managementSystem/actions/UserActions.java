package actions;

import constants.Constants;
import repository.Repository;
import util.Util;

public class UserActions {
    protected static boolean signUp(
            String username,
            String password,
            String name,
            String surname) {
        if (Util.isNullExist(username, password, name, surname, false))
            throw new RuntimeException("All parameters must be provided");

        if (UserActionsUtil.isAuthenticated())
            throw new RuntimeException("Signed in into the system already");

        if (!Repository.addNewUser(username, password, name, surname))
            return false;

        UserActionsUtil.onSignedIn(username, false);

        return true;
    }

    protected static boolean signIn(String username, String password) {
        if (Util.isNullExist(username, password))
            throw new RuntimeException("All parameters must be provided");

        if (UserActionsUtil.isAuthenticated())
            throw new RuntimeException("Signed in into the system already");

        String[] result = Repository.getPasswordAndAuthorization(username);
        if (result == null) {
            System.out.println("\nInvalid credentials");

            return false;
        }

        if (!result[0].equals(password)) {
            System.out.println("\nInvalid credentials");

            return false;
        }

        UserActionsUtil.onSignedIn(username, result[1].equals(Constants.AUTHENTICATED_ADMIN));

        return true;
    }

    protected static boolean signOut(String username) {
        if (Util.isNullExist(username))
            throw new RuntimeException("All parameters must be provided");

        if (!UserActionsUtil.isAuthenticated())
            throw new RuntimeException("Not signed in into the system");

        UserActionsUtil.onSignedOut();

        return true;
    }
}
