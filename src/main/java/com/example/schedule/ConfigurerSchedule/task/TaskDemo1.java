package com.example.schedule.ConfigurerSchedule.task;

import com.example.schedule.ConfigurerSchedule.ConfigurerScheduling;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @fileName：Task
 * @createTime：2019/5/20 10:07
 * @author：XSH
 * @version：
 * @description：
 */

@Configuration
public class TaskDemo1 extends ConfigurerScheduling {

    @Value(value = "${task.taskName1.switch}")
    private Boolean isSwitch;

    @Value(value = "${task.taskName1.cron}")
    private String cron;

    @Override
    protected void processTask() {
        if (isSwitch){
            System.out.println("基于接口SchedulingConfigurer的动态定时任务:"
                    + LocalDateTime.now()+"，线程名称："+Thread.currentThread().getName()
                    + " 线程id："+Thread.currentThread().getId());
        }
    }

    @Override
    protected String getCron() {
        return cron;
    }
}
