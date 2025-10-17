package com.example.taskapi.util;

public class CommandValidator {

    private static final String[] SAFE_COMMANDS = {"echo", "date", "pwd", "ls", "whoami"};

    public static boolean isSafe(String command) {
        if (command == null || command.isBlank()) return false;

        for (String c : new String[]{";", "&", "|", "$", ">", "<", "`"}) {
            if (command.contains(c)) return false;
        }

        String firstWord = command.split(" ")[0];
        for (String safe : SAFE_COMMANDS) {
            if (safe.equals(firstWord)) return true;
        }
        return false;
    }
}