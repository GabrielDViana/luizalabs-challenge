package com.labs.luizalabschallenge.services.strategy;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.labs.luizalabschallenge.enums.NoticeType;
import org.springframework.stereotype.Component;
import com.labs.luizalabschallenge.services.dto.NoticeDTO;

@Component
public class SmsStrategy implements NoticeStrategy {

    private static final String ACCOUNT_SID = "AC1c009f66925e669230b8948a4912ab39";
    private static final String AUTH_TOKEN = "";

    private NoticeDTO noticeDTO;

    @Override
    public void setNoticeDTO(NoticeDTO noticeDTO) {
        this.noticeDTO = noticeDTO;
    }

    @Override
    public NoticeType getNoticeType() {
        return NoticeType.SMS;
    }

    @Override
    public void run() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        // SMS has an Service SID for messaging instead of an phone number
        Message.creator(
                new com.twilio.type.PhoneNumber("+55" + this.noticeDTO.getPhoneNumber()),
                "MG52829b944080ed4010d789055ee2a5f6",
                this.noticeDTO.getMessageContent())
                .create();
    }
}
