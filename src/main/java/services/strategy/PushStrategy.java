package services.strategy;

import enums.NoticeType;
import org.springframework.stereotype.Component;
import services.dto.NoticeDTO;

@Component
public class PushStrategy implements NoticeStrategy {

    private NoticeDTO noticeDTO;

    @Override
    public void setNoticeDTO(NoticeDTO noticeDTO) {
        this.noticeDTO = noticeDTO;
    }

    @Override
    public NoticeType getNoticeType() {
        return NoticeType.PUSH;
    }

    @Override
    public void run() {
        //TODO
    }
}
