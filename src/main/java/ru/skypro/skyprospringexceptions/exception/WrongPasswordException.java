package ru.skypro.skyprospringexceptions.exception;

public class WrongPasswordException extends Throwable {
    public WrongPasswordException(String message) {
        super(message);
    }
}
