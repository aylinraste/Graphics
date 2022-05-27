package com.example.hw3.Model;

import java.util.Collections;

public class GameDatabase {
    private static User user;
    private static boolean isMute = false, isDevil = false;
    private static int difficulty = 2;

    public static void setUser(User user) {
        GameDatabase.user = user;
    }

    public static User getUser() {
        return user;
    }

    public static boolean getIsMute() {
        return isMute;
    }

    public static void setIsMute(boolean isMute) {
        GameDatabase.isMute = isMute;
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(int difficulty) {
        GameDatabase.difficulty = difficulty;
    }

    public static boolean isDevil() {
        return isDevil;
    }

    public static void setIsDevil(boolean isDevil) {
        GameDatabase.isDevil = isDevil;
    }
}
