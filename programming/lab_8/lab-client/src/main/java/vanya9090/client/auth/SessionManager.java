package vanya9090.client.auth;
import vanya9090.common.models.User;

public class SessionManager {
    public static User currentUser = null;
    public static String currentLanguage = "Русский";

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        SessionManager.currentUser = currentUser;
    }

    public static String getCurrentLanguage() {
        return currentLanguage;
    }

    public static void setCurrentLanguage(String currentLanguage) {
        SessionManager.currentLanguage = currentLanguage;
    }

}
