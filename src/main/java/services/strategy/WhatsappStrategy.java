package services.strategy;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import enums.NoticeType;
import org.springframework.stereotype.Component;

@Component
public class WhatsappStrategy implements NoticeStrategy{
    private static final String ACCOUNT_SID = "AC1c009f66925e669230b8948a4912ab39";
    private static final String AUTH_TOKEN = "";

    @Override
    public void sendNotice(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+556196302228"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                "Your Yummy Cupcakes Company order of 1 dozen frosted cupcakes has shipped and should be delivered on July 10.")
                .create();
    }
    @Override
    public NoticeType getNoticeType() {
        return NoticeType.WHATSAPP;
    }
}
