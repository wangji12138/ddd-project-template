package cn.changemax.ddd.appliction.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author WangJi
 * @Description 自动任务
 * @Date 2020/12/1 14:22
 */
@Slf4j
@Component
public final class DddJob {

    @Scheduled(cron = "0 0 9 * * ?")
    public void job1() {
        log.info("Good morning! Programmer！！");
    }
}
