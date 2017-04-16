package org.solomon.plugin.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.solomon.utils.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 基于注解实现quartz调度样例类
 * DataConversionTask
 * 
 * @author likf
 */
@Slf4j
@Component
@DisallowConcurrentExecution //任务是否可以并发 加上注解表示不可以并发
public class DataConversionTask {

    @Scheduled(cron = "0 0/1 * * * ?")//每隔1分钟执行一次。
    public void run() {
        log.info(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss")+" DataConversionTask定时任务开始执行......");
    }

}
