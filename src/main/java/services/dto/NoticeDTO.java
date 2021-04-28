package services.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.Instant;
import java.time.ZonedDateTime;

@Data
public class NoticeDTO {
    private Long id;

    @NotNull
    @Pattern(regexp = "^[0-9]*$")
    private String phoneNumber;

    @NotNull
    private ZonedDateTime scheduleDate;
}
