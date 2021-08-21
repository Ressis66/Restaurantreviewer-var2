package com.ressis.util.exception;

public class ErrorInfo {
    private final String url;
    private final ErrorType type;
    private final String typeMessage;

    public ErrorInfo(CharSequence url, ErrorType type, String typeMessage) {
        this.url = url.toString();
        this.type = type;
        this.typeMessage = typeMessage;
    }
}