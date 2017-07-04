package service.impl;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import model.User;
import service.BaseService;

public class BaseServiceImpl implements BaseService {

    @Override
    public Map<String, Object> getHttpSession() {
        return ActionContext.getContext().getSession();
    }

    @Override
    public boolean isLogined() {
        return getHttpSession().containsKey("userInfo");
    }

    @Override
    public User getLoginedUserInfo() {
        return (User)getHttpSession().get("userInfo");
    }

    @Override
    public void setLoginedUserInfo(User userInfo) {
        getHttpSession().put("userInfo", userInfo);
    }

    @Override
    public void clearLoginedUserInfo() {
        if(getHttpSession().containsKey("userInfo")) {
            getHttpSession().remove("userInfo");
        }
    }
    
}