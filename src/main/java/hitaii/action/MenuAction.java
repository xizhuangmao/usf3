package hitaii.action;

import java.util.ArrayList;
import java.util.List;

import hitaii.model.SessionInfo;
import hitaii.model.Users;
import hitaii.pageModel.Pmenu;
import hitaii.service.MenuServiceI;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;


import com.opensymphony.xwork2.ModelDriven;

@Action(value = "menuAction")
public class MenuAction extends BaseAction implements ModelDriven<Pmenu>{
	
	private Pmenu menu = new Pmenu();
	
	private MenuServiceI menuService;

	public MenuServiceI getMenuService() {
		return menuService;
	}

	@Autowired
	public void setMenuService(MenuServiceI menuService) {
		this.menuService = menuService;
	}

	@Override
	public Pmenu getModel() {
		// TODO Auto-generated method stub
		return menu;
	}
	/**
	 * 异步获取树节点
	 */
	public void getTreeNode(){
		super.writeJson(menuService.getTreeNode(menu));
	}
	
	public void doNotNeedSessionAndSecurity_getAllTreeNode(){
		
		List<Pmenu> menuList = new ArrayList<Pmenu>();
		
		try{
			SessionInfo sessionInfo = new SessionInfo();
			
			Users users = new Users();
			
			sessionInfo= (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
			
			users = sessionInfo.getUsers();
			
			
			
			menuList =menuService.getAllTreeNode(users);
		}
		catch(Exception e){
			
			e.printStackTrace();
			
		}finally{
			super.writeJson(menuList);
		}
		
		
		
	}

}
