package job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import service.UserService;  

public class MaintenanceJob extends QuartzJobBean {
    private UserService userService;
    private static int counter = 0;
    
    /* ============================================================ */
    
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println();  
        long ms = System.currentTimeMillis();  
        System.out.println("\t\t" + new Date(ms));  
        System.out.println(ms);  
        System.out.println("(" + counter++ + ")");
        try {
            userService = (UserService) context.getScheduler().getContext().get("userService");
        } catch (SchedulerException e) {     
            e.printStackTrace();
        }
        userService.checkEmailAvailable("email");  
        System.out.println();
    }
}