package com.labs.luizalabschallenge.services.mapper;

import com.labs.luizalabschallenge.domain.Notice;
import com.labs.luizalabschallenge.repository.NoticeRepository;
import com.labs.luizalabschallenge.services.dto.NoticeDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class NoticeMapper{
    @Autowired
    NoticeRepository noticeRepository;
    public NoticeDTO map(final Notice notice){
        return NoticeDTO.builder()
                .email(notice.getEmail())
                .messageContent(notice.getMessageContent())
                .noticeType(notice.getNoticeType())
                .scheduleDate(notice.getScheduleDate())
                .phoneNumber(notice.getPhoneNumber())
                .build();
    }

    public Notice toEntity(final NoticeDTO noticeDTO) {

        return Notice.builder()
                .id(noticeDTO.getId())
                .email(noticeDTO.getEmail())
                .messageContent(noticeDTO.getMessageContent())
                .noticeType(noticeDTO.getNoticeType())
                .scheduleDate(noticeDTO.getScheduleDate())
                .phoneNumber(noticeDTO.getPhoneNumber())
                .build();
    }
}
