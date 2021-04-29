package services.strategy;

import enums.NoticeType;
import org.springframework.stereotype.Component;

@Component
public class PushStrategy implements NoticeStrategy{
    @Override
    public void sendNotice(){
        // TODO
    }
    @Override
    public NoticeType getNoticeType() {
        return NoticeType.PUSH;
    }
}
