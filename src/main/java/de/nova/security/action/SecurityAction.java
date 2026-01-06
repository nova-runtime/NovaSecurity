package de.nova.security.action;

public enum SecurityAction {
    KICK,
    WARN,
    CANCEL;

    public static SecurityAction fromString(String input) {
        try {
            return SecurityAction.valueOf(input.toUpperCase());
        } catch (Exception e) {
            return KICK;
        }
    }
}
