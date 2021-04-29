package services.schedule;

import java.time.Instant;
import java.time.ZonedDateTime;

import javax.annotation.PostConstruct;

import enums.NoticeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import services.dto.NoticeDTO;
import services.strategy.NoticeStrategy;
import services.strategy.NoticeStrategyFactory;

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