package com.example.meetior.Misc;

public class Checker {
    public boolean name(String a) {
        for (int i = 0; i < a.length(); i++) {
            if ((a.charAt(i) >= 'a' && a.charAt(i) <= 'z') || (a.charAt(i) >= 'A' && a.charAt(i) <= 'Z') || a.charAt(i) == ' ')
                continue;
            return false;
        }
        return !(a.length() < 6 || a.length() > 20);
    }

    public boolean pass(String a) {
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == ' ')
                return false;
        }
        return !(a.length() < 6 || a.length() > 20);
    }
}
