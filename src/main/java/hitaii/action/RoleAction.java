package hitaii.action;

import hitaii.pageModel.Json;
import hitaii.pageModel.Prole;
import hitaii.service.RoleServiceI;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "roleAction")
public class RoleAction extends BaseAction implements ModelDriven<Prole>{
	
	private Prole prole = new Prole();
	@Override
	public Prole getModel() {
		// TODO Auto-generated method stub
		return prole;
	}
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RoleAction.class);
	
	private RoleServiceI roleService ;

	public RoleServiceI getRoleService() {
		return roleService;
	}
	@Autowired
	public void setRoleService(RoleServiceI roleService) {
		this.roleService = roleService;
	}

	public void add(){
		Json j = new Json();
		roleService.add(prole);
		j.setMsg("add role success!");
		j.setSuccess(true);
		super.writeJson(j);
	}

	public void edit(){
		Json j = new Json();
		roleService.update(prole);
		j.setMsg("edit role success!");
		j.setSuccess(true);
		super.writeJson(j);
	}
	
	public void delete(){
		Json j = new Json();
		roleService.delete(prole);
		j.setMsg("delete role success!");
		j.setSuccess(true);
		super.writeJson(j);
	}
	
	
	/*
	 * 获取所有角色
	 */
	public void datagrid(){
		
		super.writeJson(roleService.datagrid(prole));
	}
	
	/*
	 * 暂时没有用，用了上面方法
	 * 获取所有角色，并把该用户拥有的角色打勾
	 */
	public void userrolesdatagrid(){
		
		super.writeJson(roleService.userrolesdatagrid(prole));
	}
	
	/*
	 * 获取一个角色
	 */
	public void get(){
		
		super.writeJson(roleService.get(prole));
	}

}
