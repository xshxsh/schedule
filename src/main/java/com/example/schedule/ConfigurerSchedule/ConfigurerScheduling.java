package com.example.schedule.ConfigurerSchedule;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

import java.util.concurrent.*;

/**
 * @fileName：ConfigurerScheduling
 * @createTime：2019/5/14 17:26
 * @author：XSH
 * @version：
 * @description：基于接口SchedulingConfigurer的动态定时任务
 */

@Configuration
@EnableScheduling
public abstract class ConfigurerScheduling implements SchedulingConfigurer {

    /**
     * @brief 定时任务名称
     */
    private String schedulerName;

    /**
     * @brief 定时任务周期表达式
     */
    private String cron;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskScheduler());
        scheduledTaskRegistrar.addTriggerTask(
                //执行定时任务
                () -> {
                    processTask();
                },
                //设置触发器
                triggerContext -> {
                    // 初始化定时任务周期
                    if (StringUtils.isEmpty(cron)) {
                        cron = getCron();
                    }
                    CronTrigger trigger = new CronTrigger(cron);
                    return trigger.nextExecutionTime(triggerContext);
                }
        );
    }

    /**
     * 设置TaskScheduler用于注册计划任务
     *
     * @return
     */
    @Bean(destroyMethod = "shutdown")
    public Executor taskScheduler() {
        //设置线程名称
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        //创建线程池
        return Executors.newScheduledThreadPool(5, namedThreadFactory);
    }

    /**
     * @brief 任务的处理函数
     * 本函数需要由派生类根据业务逻辑来实现
     */
    protected abstract void processTask();


    /**
     * @return String
     * @brief 获取定时任务周期表达式
     * 本函数由派生类实现，从配置文件，数据库等方式获取参数值
     */
    protected abstract String getCron();
}
