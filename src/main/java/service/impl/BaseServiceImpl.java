package service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

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
    
    @Override
    public String getBasePath() {
        /*
         * 类似于jsp中的basePath，主要用来处理发送邮件时的url路径问题，例如新用户的激活链接
         * 注意，这里的basePath结尾没有"/"，与jsp中的不同！
         * basePath的一个示例如下（不包括引号）：
         *     "http://127.0.0.1:8080/bookshare"
         */
        HttpServletRequest request = ServletActionContext.getRequest();  
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path;
        return basePath;
    }
}