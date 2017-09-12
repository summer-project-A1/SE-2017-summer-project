package service.impl;

import java.util.Date;
import java.util.List;

import common.constants.UserStatus;
import dao.UserDao;
import model.User;
import service.JobService;

public class JobServiceImpl extends BaseServiceImpl implements JobService {

    private UserDao userDao;
    
    /* ============================================================ */
    
    public UserDao getUserDao() {
        return userDao;
    }
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    /* ============================================================ */
    
    @Override
    public void deleteAllTimeoutUnactiveUser() {
        // 删除所有超时未激活的用户，固定时间执行一次
        List<User> users = this.userDao.getAllTimeoutUnactiveUser();
        if(users != null) {
            for(User user : users) {
                try {
                    this.userDao.deleteUser(user);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @Override
    public void deleteTimeoutUnactiveUser(Integer userID) {
        // 当该用户的激活期限达到但未激活，则删除该用户
        User user = this.userDao.getUserById(userID);
        if(user.getStatus().equals(UserStatus.UNACTIVATED)) {  // 如果用户未激活
            Date due = user.getDue();
            Date now = new Date();
            if(now.after(due)) {  // 如果当前时间超过了截至时间
                this.userDao.deleteUser(user);
            }
        }
    }
}