package com.lj.music_server.exeption;

public class NoneAuthException extends RuntimeException {
    public NoneAuthException(String message) {
        super(message);
    }
}
