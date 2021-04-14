package com.github.klepus;


import com.github.klepus.model.Meal;

import static com.github.klepus.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL1_ID = START_SEQ + 4;
    public static final int MEAL2_ID = START_SEQ + 5;
    public static final int MEAL3_ID = START_SEQ + 6;
    public static final int MEAL4_ID = START_SEQ + 7;
    public static final int MEAL5_ID = START_SEQ + 8;
    public static final int MEAL6_ID = START_SEQ + 9;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, "еда_1");
    public static final Meal MEAL2 = new Meal(MEAL2_ID, "еда_2");
    public static final Meal MEAL3 = new Meal(MEAL3_ID, "еда_3");
    public static final Meal MEAL4 = new Meal(MEAL4_ID, "еда_4");
    public static final Meal MEAL5 = new Meal(MEAL5_ID, "еда_5");
    public static final Meal MEAL6 = new Meal(MEAL6_ID, "еда_6");
}
