package com.example.schedule.SimpleSchedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @fileName：Schedule
 * @createTime：2019/5/14 17:16
 * @author：XSH
 * @version：
 * @description：基于注解(@Scheduled)的简单定时器demo
 *
 * cron表达式语法:[秒] [分] [小时] [日] [月] [周] [年]
 * @Scheduled(fixedDelay = 5000) //上一次执行完毕时间点之后5秒再执行
 * @Scheduled(fixedDelayString = "5000") //上一次执行完毕时间点之后5秒再执行
 * @Scheduled(fixedRate = 5000) //上一次开始执行时间点之后5秒再执行
 * @Scheduled(initialDelay=1000, fixedRate=5000) //第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
 *
 */


//1.主要用于标记配置类
@Configuration
// 2.开启定时任务
@EnableScheduling
public class Schedule {
    //3.添加定时任务
    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)

    private void configureTasks() {
        System.err.println("基于注解(@Scheduled)的简单定时器demo: " + LocalDateTime.now());
    }
}