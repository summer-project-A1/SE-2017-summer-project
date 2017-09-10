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
import service.UserService;

public class UnactiveUserJob extends QuartzJobBean {

    private UserService userService;
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        try {
            SchedulerContext schedulerContext = context.getScheduler().getContext();
            userService = (UserService) schedulerContext.get("userService");
        } catch (SchedulerException e) {     
            e.printStackTrace();
        }
        Integer userID = (Integer)dataMap.get("userID");
        //userService.deleteTimeoutUnactiveUser(userID);
        System.out.println("start");      ///////////////////////////////////////////////////////////////////////////////
        System.out.println(userID);      ///////////////////////////////////////////////////////////////////////////////
        userService.checkEmailAvailable("email");  //////////////////////////////////////////////////////////////////////
        System.out.println("end");        ///////////////////////////////////////////////////////////////////////////////
    }
    
}