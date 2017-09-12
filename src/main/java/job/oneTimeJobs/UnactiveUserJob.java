package job.oneTimeJobs;

import java.util.HashMap;
import java.util.Map;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import job.QuartzManager;
import service.JobService;
import service.UserService;

public class UnactiveUserJob extends QuartzJobBean {

    private JobService jobService;
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        try {
            SchedulerContext schedulerContext = context.getScheduler().getContext();
            jobService = (JobService) schedulerContext.get("jobService");
        } catch (SchedulerException e) {     
            e.printStackTrace();
        }
        Integer userID = (Integer)dataMap.get("userID");
        //userService.deleteTimeoutUnactiveUser(userID);
        System.out.println("start");      ///////////////////////////////////////////////////////////////////////////////
        System.out.println(userID);      ///////////////////////////////////////////////////////////////////////////////
        System.out.println("end");        ///////////////////////////////////////////////////////////////////////////////
    }
    
}