package actions;

public class UserActionsUtil {
    private static AuthenticatedUser authenticatedUser;

    protected static void onSignedIn(String username, boolean admin) {
        if (authenticatedUser != null)
            throw new RuntimeException("Signed in into the system already");

        authenticatedUser = new AuthenticatedUser(username, admin);
    }

    protected static void onSignedOut() {
        authenticatedUser = null;
    }

    protected static boolean isAuthenticated() {
        return authenticatedUser != null;
    }

    protected static boolean isUserAuthenticated(String username) {
        if (authenticatedUser == null)
            return false;

        return authenticatedUser.getUsername().equals(username);
    }

    protected static boolean isAuthenticatedUserAdmin() {
        if (authenticatedUser == null)
            throw new RuntimeException("No auhenticated user");

        return authenticatedUser.isAdmin();
    }

    protected static String getAuthenticatedUsername() {
        if (authenticatedUser == null)
            throw new RuntimeException("No authenticated user");

        return authenticatedUser.getUsername();
    }
}

class AuthenticatedUser {
    private final String username;
    private final boolean admin;

    protected AuthenticatedUser(String username, boolean admin) {
        this.username = username;
        this.admin = admin;
    }

    protected String getUsername() {
        return username;
    }

    protected boolean isAdmin() {
        return admin;
    }
}