package services.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import enums.NoticeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoticeStrategyFactory {
    private Map<NoticeType, NoticeStrategy> strategies;

    @Autowired
    public NoticeStrategyFactory(Set<NoticeStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public NoticeStrategy findStrategy(NoticeType strategyName) {
        return strategies.get(strategyName);
    }
    private void createStrategy(Set<NoticeStrategy> strategySet) {
        strategies = new HashMap<NoticeType, NoticeStrategy>();
        strategySet.forEach(
                strategy ->strategies.put(strategy.getNoticeType(), strategy));
    }
}