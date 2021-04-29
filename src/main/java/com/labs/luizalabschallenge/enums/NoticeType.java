package com.labs.luizalabschallenge.enums;

public enum NoticeType {
    SMS("SMS"),
    WHATSAPP("WHATSAPP"),
    EMAIL("EMAIL"),
    PUSH("PUSH");

    private String type;
    NoticeType(String type) {
        this.type = type;
    }
}
