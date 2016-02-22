package uk.co.boombastech.logging;

public interface Logging {

    default void log(LogLevel logLevel, String message) {
        String className = this.getClass().getName();
        System.out.println(String.format("%s - %s: %s", logLevel, className, message));
    }
}