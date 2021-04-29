package services.strategy;

import enums.NoticeType;
import org.springframework.stereotype.Component;
import services.dto.NoticeDTO;

@Component
public class EmailStrategy implements NoticeStrategy {

    private NoticeDTO noticeDTO;

    @Override
    public void setNoticeDTO(NoticeDTO noticeDTO) {
        this.noticeDTO = noticeDTO;
    }

    @Override
    public NoticeType getNoticeType() {
        return NoticeType.EMAIL;
    }

    @Override
    public void run() {
        //TODO
    }
}
