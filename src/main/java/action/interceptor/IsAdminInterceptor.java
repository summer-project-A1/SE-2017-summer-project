package action.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import common.constants.UserRole;
import model.User;

import java.util.Map;

/**
 * Created by zzy on 2017/7/5.
 */
public class IsAdminInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        Map session=ai.getInvocationContext().getSession();
        if(session.containsKey("userInfo"))
        {
            User userInfo =(User) session.get("userInfo");
            if(userInfo.getRole()== UserRole.ADMIN){
                return ai.invoke();
            }
            else
            {
                return "403";
            }
        }
        return Action.LOGIN;
    }
}
