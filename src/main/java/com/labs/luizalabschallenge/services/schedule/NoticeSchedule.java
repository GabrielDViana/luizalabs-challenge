package com.labs.luizalabschallenge.services.schedule;

import java.time.Instant;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import com.labs.luizalabschallenge.services.dto.NoticeDTO;
import com.labs.luizalabschallenge.services.strategy.NoticeStrategyFactory;

@Component
public class NoticeSchedule {
    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    NoticeStrategyFactory noticeStrategyFactory;

    @PostConstruct
    public void scheduleRunnableTrigger(final NoticeDTO notice) {
        noticeStrategyFactory.setNoticeDTO(notice);
        taskScheduler.schedule(noticeStrategyFactory.findStrategy(notice.getNoticeType()), Instant.from(notice.getScheduleDate()));
    }

}