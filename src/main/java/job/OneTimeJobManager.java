package job;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import job.oneTimeJobs.UnactiveUserJob;

public class OneTimeJobManager {

    private QuartzManager quartzManager;

    /* ============================================================= */
    
    public QuartzManager getQuartzManager() {
        return quartzManager;
    }
    public void setQuartzManager(QuartzManager quartzManager) {
        this.quartzManager = quartzManager;
    }

    /* ============================================================= */

    public void addUnactiveUserJob(Integer userID, Integer delayMillisecond) {
        Long currentTimeMillis = System.currentTimeMillis();
        String jobName = "job?userID="+userID+"&timeStamp="+currentTimeMillis;
        String jobGroupName = "unactiveUser";
        String triggerName = "trigger?userID="+userID+"&timeStamp="+currentTimeMillis;
        String triggerGroupName = "unactiveUser";
        Class jobClass = UnactiveUserJob.class;
        Long startTimeMillis = currentTimeMillis + delayMillisecond;
        Date startTime = new Date(startTimeMillis);
        Map args = new HashMap();
        args.put("userID", userID);
        quartzManager.addSimpleTriggerJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, startTime, null, 0, 0, args);
        System.out.println("addUnactiveJob success");  //////////////////////////////////////////////////////////////////
    }
}