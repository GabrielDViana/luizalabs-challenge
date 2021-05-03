package com.labs.luizalabschallenge.services.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.labs.luizalabschallenge.enums.NoticeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    private Long id;

    @NotNull
    @Pattern(regexp = "^[0-9]*$")
    private String phoneNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mmZ")
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
