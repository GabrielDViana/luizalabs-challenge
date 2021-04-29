package services.strategy;

import enums.NoticeType;
import services.dto.NoticeDTO;

public interface NoticeStrategy extends Runnable {
    void setNoticeDTO(NoticeDTO noticeDTO);
    NoticeType getNoticeType();
}
