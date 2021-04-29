package com.labs.luizalabschallenge.services.dto;

import com.labs.luizalabschallenge.enums.NoticeType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Data
public class NoticeDTO {
    private Long id;

    @NotNull
    @Pattern(regexp = "^[0-9]*$")
    private String phoneNumber;

    @NotNull
    private ZonedDateTime scheduleDate;

    @NotNull
    private NoticeType noticeType;

    @NotNull
    private String messageContent;

    @NotNull
    @Size(min = 5, max = 100)
    private String email;
}
