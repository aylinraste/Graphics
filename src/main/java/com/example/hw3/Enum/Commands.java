package com.example.hw3.Enum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    USERNAMEFORMATREGEX("[a-zA-Z0-9_]+"),
    PASSWORDFORMATREGEX("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\*\\.\\!\\@\\$\\%\\^\\&\\(\\)\\{\\}\\[\\]\\:\\;\\<\\>\\,\\?\\/\\~\\_\\+\\-\\=\\|]).{8,32}");
    private final String regex;

    Commands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, Commands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }

}
