package ru.catn.auth;

import ru.catn.core.model.User;

public class CurrentUser {
    private static User user;

    private CurrentUser() {
    }

    public static User get() {
        if (user == null) {
            User currentUser = new User();
            currentUser.setId(1);
            user = currentUser;
        }
        return user;
    }
}
