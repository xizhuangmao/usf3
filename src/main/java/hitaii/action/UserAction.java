package hitaii.action;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hitaii.constant.Constant;
import hitaii.model.SessionInfo;
import hitaii.model.Role;
import hitaii.model.Users;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Puser;
import hitaii.service.UserServiceI;
import hitaii.service.WhesdtlServiceI;
import hitaii.service.WhesdtlServicesServiceI;
import hitaii.util.ImageUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Copy Right Information : hitaii
 * 
 * Function :用户管理
 * 
 * Author : zw
 * 
 * Date : 20160111
 * 
 * Modification history :
 * 
 */
@Namespace("/")
@Action(value = "userAction")
public class UserAction extends BaseAction implements ModelDriven<Puser>{
	
	private Puser puser = new Puser();
	@Override
	public Puser getModel() {
		// TODO Auto-generated method stub
		return puser;
	}
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserAction.class);
	
	private UserServiceI userService ;
	private WhesdtlServiceI whesdtlService;
	
	public WhesdtlServiceI getWhesdtlService() {
		return whesdtlService;
	}
	@Autowired
	public void setWhesdtlService(WhesdtlServiceI whesdtlService) {
		this.whesdtlService = whesdtlService;
	}
	public UserServiceI getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}


	/**
	 * 注册新用户
	 */
	public void doNotNeedSessionAndSecurity_reg(){

		Json j = new Json();
		
		logNameCantBeAdmin(j);
		
		try{
			userService.save(puser);
			j.setSuccess(true);
			j.setMsg("Welcome!");
		}catch(Exception e){
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	
	/**
	 * 增加用户
	 */
	public void add(){

		Json j = new Json();
		
		logNameCantBeAdmin(j);
		
		try{
			Puser u =userService.save(puser);
			j.setSuccess(true);
			j.setMsg("Success");
			j.setObj(u);
		}catch(Exception e){
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	
	/**
	 * 登录
	 */
	public void doNotNeedSessionAndSecurity_login(){
	
		HttpServletRequest request = ServletActionContext.getRequest();
		Json j = new Json();
		String code = (String) request.getSession().getAttribute(
				Constant.VALIDATECODE_KEY);
		//校验验证码
		//if(null!=puser.getValidate()&&puser.getValidate().equalsIgnoreCase(code)){
		if(true){			
			Users user = userService.login(puser);

			if(null != user){
				//用户未激活，直接返回
				if(null==user.getActive()||!user.getActive().equalsIgnoreCase("1")){
					j.setMsg("The user not active！");
					super.writeJson(j);
				}else{
					//这里初始化user下的roles和resources,不然session里面的用户角色和资源不会被初始化
					Hibernate.initialize(user.getRoles());
					for (Role trole : user.getRoles()) {
						Hibernate.initialize(trole.getResources());
					}
					Hibernate.initialize(user.getCompanies());
					Hibernate.initialize(user.getWheses());
					Hibernate.initialize(user.getNvoccs());
					Hibernate.initialize(user.getCarriers());
					Hibernate.initialize(user.getTrucks());
					SessionInfo sessionInfo = new SessionInfo();
					sessionInfo.setUsers(user);
					ServletActionContext.getRequest().getSession().setAttribute("sessionInfo", sessionInfo);
					
					Puser uPuser = new Puser();
					BeanUtils.copyProperties(user, uPuser);
					j.setObj(uPuser);
					j.setSuccess(true);
					j.setMsg("Welcome！");
				}
			}else{
				j.setSuccess(false);
				j.setMsg("Sign in failed，wrong ID or Password!");
			}
		}else{
			j.setSuccess(false);
			j.setMsg("wrong validate code!");
		}

		super.writeJson(j);
		
	}
	
	/**
	 * 注销
	 */
	public void doNotNeedSessionAndSecurity_logout(){
		

		Json j = new Json();
		
		ServletActionContext.getRequest().getSession().removeAttribute("sessionInfo");
		j.setSuccess(true);
		j.setMsg("Sign out success!");

		super.writeJson(j);
		
	}
	
	/**
	 * 删除用户
	 */
	public void remove(){
		
		Json j = new Json();
		
		adminCantBeModify(j);
		
		if(StringUtils.isBlank(puser.getIds())){
			j.setMsg("please select a puser");
			j.setSuccess(false);
		}else if(Arrays.asList(puser.getIds().split(",")).contains("1")){
			j.setMsg("admin can not delete");
			j.setSuccess(false);
		}else{
			userService.remove(puser);
			j.setSuccess(true);
			j.setMsg("delete success!");
		}
		
		
		super.writeJson(j);
	}
	
	/**
	 * 编辑用户，除了用户的公司和角色
	 */
	public void edit(){
		
		Json j = new Json();
		
		adminCantBeModify( j );
		
		try {
			//先排除公司和角色
			puser.setUserses(null);
			puser.setCompanies(null);
			puser.setRoles(null);
			puser.setWheses(null);
			puser.setNvoccs(null);
			puser.setCarriers(null);
			puser.setTrucks(null);
			Puser u = userService.edit(puser);
			j.setSuccess(true);
			j.setMsg("edit success!");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	
	/**
	 * 获取一个用户
	 */
	public void get(){
		
		Json j = new Json();
		try{
			Puser u = userService.get(puser);
			j.setSuccess(true);
			j.setMsg("success!");
			j.setObj(u);
		}catch(Exception e){
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	
	/**
	 * 1、编辑用户角色
	 */
	public void editRole(){
		
		Json j = new Json();
		try{
			Puser u = userService.editRole(puser);
			j.setSuccess(true);
			j.setMsg("grant role success!");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	
	/**
	 * 1.5、编辑用户公司
	 */
	public void editCompany(){
		
		Json j = new Json();
		try {
			Puser u = userService.editCompany(puser);
			j.setSuccess(true);
			j.setMsg("grant company success!");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	
	/**
	 * 2、编辑用户仓库
	 */
	public void editWhes(){
		
		Json j = new Json();
		try {
			Puser u = userService.editWhes(puser);
			j.setSuccess(true);
			j.setMsg("grant warehouse success!");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	
	/**
	 * 3、编辑用户nvocc
	 */
	public void editNvocc(){
		
		Json j = new Json();
		try {
			Puser u = userService.editNvocc(puser);
			j.setSuccess(true);
			j.setMsg("grant nvocc success!");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	
	/**
	 * 4、编辑用户航运公司
	 */
	public void editCarrier(){
		
		Json j = new Json();
		try {
			Puser u = userService.editCarrier(puser);
			j.setSuccess(true);
			j.setMsg("grant carrier success!");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	
	/**
	 * 5、编辑用户卡车公司
	 */
	public void editTruck(){
		
		Json j = new Json();
		try {
			Puser u = userService.editTruck(puser);
			j.setSuccess(true);
			j.setMsg("grant truck success!");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	/**
	 * 获取用户datagrid
	 */
	public void datagrid(){
		
		super.writeJson(userService.dataGrid(puser));
	}
	
	
	/**
	 * 修改密码
	 * id只能从session中获取，从前台获取会有安全问题
	 */
	public void editPassword(){
		
		Json j = new Json();
		try{
			SessionInfo sessionInfo = new SessionInfo();
			
			sessionInfo= (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
			puser.setId(sessionInfo.getUsers().getId());
			Puser u = userService.edit(puser);
			j.setSuccess(true);
			j.setMsg("change password success!");
			j.setObj(u);
		}catch(Exception e){
			j.setMsg("change password failed!");
			e.printStackTrace();
		}finally{
			super.writeJson(j);
		}
	}
	
	/**
	 * 获得验证码
	 * @param request
	 * @param response
	 */
	public void doNotNeedSessionAndSecurity_getValCode() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		try {
			ImageUtil.makeValCode(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//admin不能被修改的验证
	private void adminCantBeModify(Json j){

		if(null!=puser.getLogname()&&puser.getLogname().equalsIgnoreCase("admin")){
			j.setMsg("admin can't be modify!");
			super.writeJson(j);
		}else if(null!=puser.getId()&&puser.getId().equalsIgnoreCase("admin")){
			j.setMsg("admin can't be modify!");
			super.writeJson(j);
		}
	}
	
	//用户logname不能含有admin，全称不能是admin或者administrator
	private void logNameCantBeAdmin(Json j){
		
		if(null!=puser.getLogname()&&puser.getLogname().contains("admin")){
			j.setMsg("logname can't contains string admin!");
			super.writeJson(j);
		}else if(null!=puser.getFullname()&&(puser.getFullname().equalsIgnoreCase("admin")||puser.getFullname().equalsIgnoreCase("administrator"))){
			j.setMsg("logname can't contains string admin!");
			super.writeJson(j);
		}
		
	}
	
}
