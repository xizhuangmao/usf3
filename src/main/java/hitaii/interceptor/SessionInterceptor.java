package hitaii.interceptor;

import hitaii.model.SessionInfo;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * session拦截器
 * 
 * @author 孙宇
 * 
 */
public class SessionInterceptor extends MethodFilterInterceptor {

	private static final Logger logger = Logger.getLogger(SessionInterceptor.class);

	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		logger.info("2/进入session拦截器->访问路径为[" + ServletActionContext.getRequest().getServletPath() + "]");
		if (sessionInfo == null) {
			String errMsg = "2/SessionInterceptor:您还没有登录或登录已超时，请重新登录，然后再刷新本功能！";
			logger.info(errMsg);
			ServletActionContext.getRequest().setAttribute("msg", errMsg);
			return "noSession";
		}
		return actionInvocation.invoke();
	}

}
