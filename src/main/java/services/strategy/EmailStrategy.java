package services.strategy;

import com.sendgrid.*;
import enums.NoticeType;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import services.dto.NoticeDTO;

import java.io.IOException;

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

    @SneakyThrows
    @Override
    public void run() {
        Email from = new Email("test@example.com");
        String subject = "Scheduled Notice Here";
        Email to = new Email(this.noticeDTO.getEmail());
        Content content = new Content("text/plain", this.noticeDTO.getMessageContent());
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sg.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }
}
