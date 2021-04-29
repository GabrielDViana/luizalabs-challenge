package services.strategy;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import enums.NoticeType;
import org.springframework.stereotype.Component;
import services.dto.NoticeDTO;

@Component
public class WhatsappStrategy implements NoticeStrategy {
    private static final String ACCOUNT_SID = "AC1c009f66925e669230b8948a4912ab39";
    private static final String AUTH_TOKEN = "";

    private NoticeDTO noticeDTO;

    @Override
    public void setNoticeDTO(NoticeDTO noticeDTO) {
        this.noticeDTO = noticeDTO;
    }

    @Override
    public NoticeType getNoticeType() {
        return NoticeType.WHATSAPP;
    }

    @Override
    public void run() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+55" + this.noticeDTO.getPhoneNumber()),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                this.noticeDTO.getMessageContent())
                .create();
    }
}
