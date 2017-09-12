package service;

public interface JobService extends BaseService {
    public void deleteAllTimeoutUnactiveUser();
    public void deleteTimeoutUnactiveUser(Integer userID);
}