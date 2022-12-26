package ru.kpfu.itis.cw.exceptions;

public class DbDriverException extends Exception {
    public DbDriverException() {
        super();
    }

    public DbDriverException(String message) {
        super(message);
    }

    public DbDriverException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbDriverException(Throwable cause) {
        super(cause);
    }

    protected DbDriverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
