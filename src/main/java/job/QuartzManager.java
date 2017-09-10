package job;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

public class QuartzManager {

    private Scheduler scheduler;
    
    /* =============================================================== */
    
    public Scheduler getScheduler() {
        return scheduler;
    }
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /* =============================================================== */
    
    /** 
     * @Description: 添加一个SimpleTrigger的定时任务 
     *  
     * @param jobName 任务名 
     * @param jobGroupName  任务组名 
     * @param triggerName 触发器名 
     * @param triggerGroupName 触发器组名 
     * @param jobClass  任务 
     * @param startTime 任务开始时间
     * @param endTime 任务结束时间
     * @param repeatCount 任务重复次数
     * @param repeatInterval 重复任务的间隔时间（秒）
     * @param args 任务所需的参数
     */  
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    public void addSimpleTriggerJob(String jobName, String jobGroupName, 
            String triggerName, String triggerGroupName, Class jobClass, Date startTime, Date endTime, Integer repeatCount, Integer repeatInterval, Map args) {  
        try {
            // 任务名，任务组，任务执行类
            JobDetail jobDetail= JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();

            // 参数
            if(args != null) {
                Iterator it =  args.entrySet().iterator();
                while(it.hasNext()) {
                    Entry entry = (Entry)it.next();
                    jobDetail.getJobDataMap().put((String) entry.getKey(), entry.getValue());
                }
            }
            
            // 触发器  
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组  
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startAt(startTime);
            // 触发器时间设定  
            triggerBuilder.withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(repeatInterval)
                    .withRepeatCount(repeatCount)
                    );
            // 创建Trigger对象
            SimpleTrigger trigger = (SimpleTrigger) triggerBuilder.build();  

            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);  

            // 启动  
            /*
            if (!scheduler.isShutdown()) {
                scheduler.start();  
            }
            */ 
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }
    
    /** 
     * @Description: 添加一个定时任务 
     *  
     * @param jobName 任务名 
     * @param jobGroupName  任务组名 
     * @param triggerName 触发器名 
     * @param triggerGroupName 触发器组名 
     * @param jobClass  任务 
     * @param cron   时间设置，参考quartz说明文档  
     */  
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    public void addJob(String jobName, String jobGroupName, 
            String triggerName, String triggerGroupName, Class jobClass, String cron, Map args) {  
        try {
            // 任务名，任务组，任务执行类
            JobDetail jobDetail= JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();

            // 参数
            if(args != null) {
                Iterator it =  args.entrySet().iterator();
                while(it.hasNext()) {
                    Entry entry = (Entry)it.next();
                    jobDetail.getJobDataMap().put((String) entry.getKey(), entry.getValue());
                }
            }
            
            // 触发器  
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组  
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定  
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);  

            // 启动  
            /*
            if (!scheduler.isShutdown()) {  
                scheduler.start();  
            } 
            */ 
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  

    /** 
     * @Description: 修改一个任务的触发时间
     *  
     * @param jobName 
     * @param jobGroupName
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名 
     * @param cron   时间设置，参考quartz说明文档   
     */  
    public void modifyJobTime(String jobName, 
            String jobGroupName, String triggerName, String triggerGroupName, String cron) {  
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);  
            if (trigger == null) {  
                return;  
            }  

            String oldTime = trigger.getCronExpression();  
            if (!oldTime.equalsIgnoreCase(cron)) { 
                /** 方式一 ：调用 rescheduleJob 开始 */
                // 触发器  
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组  
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定  
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                scheduler.rescheduleJob(triggerKey, trigger);
                /** 方式一 ：调用 rescheduleJob 结束 */

                /** 方式二：先删除，然后在创建一个新的Job  */
                //JobDetail jobDetail = sched.getJobDetail(JobKey.jobKey(jobName, jobGroupName));  
                //Class<? extends Job> jobClass = jobDetail.getJobClass();  
                //removeJob(jobName, jobGroupName, triggerName, triggerGroupName);  
                //addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron); 
                /** 方式二 ：先删除，然后在创建一个新的Job */
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  

    /** 
     * @Description: 移除一个任务 
     *  
     * @param jobName 
     * @param jobGroupName 
     * @param triggerName 
     * @param triggerGroupName 
     */  
    public void removeJob(String jobName, String jobGroupName,  
            String triggerName, String triggerGroupName) {  
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

            scheduler.pauseTrigger(triggerKey);// 停止触发器  
            scheduler.unscheduleJob(triggerKey);// 移除触发器  
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  

    /** 
     * @Description:启动所有定时任务 
     */  
    public void startJobs() {  
        try {  
            scheduler.start();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  

    /** 
     * @Description:暂停所有定时任务 ，可以重新start
     */  
    public void standbyJobs() {
        try {  
            scheduler.standby();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }
    
    /** 
     * @Description:关闭所有定时任务 ，无法重新start
     */  
    public void shutdownJobs() {  
        try {  
            if (!scheduler.isShutdown()) {  
                scheduler.shutdown();  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
}