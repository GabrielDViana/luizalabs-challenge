package domain;

import enums.NoticeType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "notice")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Pattern(regexp = "^[0-9]*$")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(name = "schedule_date", nullable = false)
    private ZonedDateTime scheduleDate;

    @NotNull
    @Column(name = "notice_type", nullable = false)
    private NoticeType noticeType;

    @NotNull
    @Column(name = "message_content", nullable = false)
    private String messageContent;

    @NotNull
    @Email
    @Size(min = 5, max = 100)
    @Column(name = "email", nullable = false)
    private String email;

}
