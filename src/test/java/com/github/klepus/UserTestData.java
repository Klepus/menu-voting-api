package com.github.klepus;


import com.github.klepus.model.User;

import static com.github.klepus.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int ADMIN_ID = START_SEQ;
    public static final int USER1_ID = START_SEQ + 1;
    public static final int USER2_ID = START_SEQ + 2;
    public static final int USER3_ID = START_SEQ + 3;

    public static final User ADMIN = new User(ADMIN_ID, "admin", "admin@gmail.com", "admin", true);
    public static final User USER1 = new User(USER1_ID, "user_1", "user_1@gmail.com", "user_1", false);
    public static final User USER2 = new User(USER2_ID, "user_2", "user_2@gmail.com", "user_2", false);
    public static final User USER3 = new User(USER3_ID, "user_3", "user_3@gmail.com", "user_3", false);

    public static User getForCreation() {
        return new User("new_user", "new_user@gmail.com", "new_user", false);
    }
}
