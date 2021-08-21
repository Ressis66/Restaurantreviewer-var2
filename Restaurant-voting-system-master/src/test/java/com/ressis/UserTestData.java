package com.ressis;

import com.ressis.model.User;

import static com.ressis.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int ADMIN_ID = START_SEQ;
    public static final int USER1_ID = START_SEQ + 1;
    public static final int USER2_ID = START_SEQ + 2;
    public static final int USER3_ID = START_SEQ + 3;

    public static final User ADMIN = new User(ADMIN_ID, "Petr_admin", "petr@gmail.com", "petr_pass", true);
    public static final User USER1 = new User(USER1_ID, "Ivan_user", "ivan@mail.ru", "ivan_pass", false);
    public static final User USER2 = new User(USER2_ID, "Dmitriy_user", "dmitriy@mail.ru", "dmitriy_pass", false);
    public static final User USER3 = new User(USER3_ID, "Vasya_user", "vasya@gmail.com", "vasya_pass", false);

    public static User getForCreation() {
        return new User("new_user", "test.mail@mail.ru", "pass123", false);
    }
}
