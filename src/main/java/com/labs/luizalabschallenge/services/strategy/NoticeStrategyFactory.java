package com.labs.luizalabschallenge.services.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.labs.luizalabschallenge.enums.NoticeType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.labs.luizalabschallenge.services.dto.NoticeDTO;

@Setter
@Getter
@Component
public class NoticeStrategyFactory {
    private Map<NoticeType, NoticeStrategy> strategies;

    private NoticeDTO noticeDTO;

    @Autowired
    public NoticeStrategyFactory(Set<NoticeStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public NoticeStrategy findStrategy(NoticeType strategyName) {
        NoticeStrategy strategy = strategies.get(strategyName);
        strategy.setNoticeDTO(this.noticeDTO);
        return strategy;
    }
    private void createStrategy(Set<NoticeStrategy> strategySet) {
        strategies = new HashMap<NoticeType, NoticeStrategy>();
        strategySet.forEach(
                strategy ->strategies.put(strategy.getNoticeType(), strategy));
    }
}