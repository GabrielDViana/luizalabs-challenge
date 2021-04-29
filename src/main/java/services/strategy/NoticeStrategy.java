package services.strategy;

import enums.NoticeType;

public interface NoticeStrategy {
    void sendNotice();
    NoticeType getNoticeType();
}
