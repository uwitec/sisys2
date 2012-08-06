package com.sisys.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sisys.action.LoginAction;
import com.sisys.bean.User;


public class CheckLoginInterceptor extends AbstractInterceptor {

	public static final String USER = "user";
	public static final String LOGIN_PAGE = "login";
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		ActionContext ctx = invocation.getInvocationContext();
		Object action = invocation.getAction();

        if (action instanceof LoginAction) {

            System.out.println("exit check login, because this is login action.");

            return invocation.invoke();

        }

        // 确认Session中是否存在USER

        Map session = invocation.getInvocationContext().getSession();

        User user = (User) session.get(USER);

        if (user != null) {

            // 存在的情况下进行后续操作。

            System.out.println("already login!");

            return invocation.invoke();

        } else {

            // 否则终止后续操作，返回LOGIN

            System.out.println("no login, forward login page!");

          //没有登陆，将服务器提示设置成一个HttpServletRequest属性  
            ctx.put("tip","您还没有登录，请登陆系统");  
            return Action.LOGIN; 
            //return LOGIN_PAGE;

        }
	}

}
