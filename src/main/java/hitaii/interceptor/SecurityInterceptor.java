package hitaii.interceptor;


import hitaii.model.SessionInfo;
import hitaii.model.Resource;
import hitaii.model.Role;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 权限拦截器
 * 
 * @author 孙宇
 * 
 */
public class SecurityInterceptor extends MethodFilterInterceptor {

	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);

	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//ActionContext actionContext = actionInvocation.getInvocationContext();
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		String servletPath = ServletActionContext.getRequest().getServletPath();

		servletPath = StringUtils.substringBeforeLast(servletPath, ".");// 去掉后面的后缀 *.sy *.action之类的

		logger.info("3/进入权限拦截器->访问的资源为：[" + servletPath + "]");

		Set<Role> roles = sessionInfo.getUsers().getRoles();
		for (Role role : roles) {
			for (Resource resource : role.getResources()) {
				if (resource != null && StringUtils.equals(resource.getUrl(), servletPath)) {
					return actionInvocation.invoke();
				}
			}
		}
	

		String errMsg = "3/SecurityInterceptor:您没有访问此功能的权限！功能路径为[" + servletPath + "]请联系管理员给你赋予相应权限。";
		logger.info(errMsg);
		ServletActionContext.getRequest().setAttribute("msg", errMsg);
		return "noSecurity";
	}

}
