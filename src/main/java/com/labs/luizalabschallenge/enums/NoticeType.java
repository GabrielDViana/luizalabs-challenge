package com.labs.luizalabschallenge.enums;

public enum NoticeType {
    SMS("SMS"),
    WHATSAPP("WHATSAPP"),
    EMAIL("EMAIL"),
    PUSH("PUSH");

    private String type;

    public String getNoticeDesc(){
        return this.type;
    }
    NoticeType(String type) {
        this.type = type;
    }
}
