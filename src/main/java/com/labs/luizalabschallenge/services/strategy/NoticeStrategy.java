package com.labs.luizalabschallenge.services.strategy;

import com.labs.luizalabschallenge.enums.NoticeType;
import com.labs.luizalabschallenge.services.dto.NoticeDTO;

public interface NoticeStrategy extends Runnable {
    void setNoticeDTO(NoticeDTO noticeDTO);
    NoticeType getNoticeType();
}
