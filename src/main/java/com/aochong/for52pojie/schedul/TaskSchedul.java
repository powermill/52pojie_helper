package com.aochong.for52pojie.schedul;

import com.aochong.for52pojie.handler.ReplayHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author zhangaochong
 * @date 2019-03-02 12:59
 */
@Component
@Slf4j
public class TaskSchedul {

    @Autowired
    private ReplayHandler replayHandler;

    /**
     * 定时回帖
     * 每6分钟执行一次
     */
    @Scheduled(cron = "0 0/6 * * * ?")
    private void replay() {
        log.info("[执行任务开始] ------------------------------");
        boolean result = replayHandler.replay();
        log.info("[执行任务结束] {} ----------------------", result?"执行成功":"执行失败");
    }

}
