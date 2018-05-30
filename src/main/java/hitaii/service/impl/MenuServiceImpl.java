package hitaii.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.MenuDaoI;
import hitaii.dao.ResourceDaoI;
import hitaii.dao.UserDaoI;
import hitaii.model.Menu;
import hitaii.model.Resource;
import hitaii.model.Role;
import hitaii.model.Users;
import hitaii.pageModel.Pmenu;
import hitaii.service.MenuServiceI;

@Service("menuService")
public class MenuServiceImpl implements MenuServiceI {
	
	private static final Logger logger = Logger
			.getLogger(UserServiceImpl.class);
	
	private ResourceDaoI resourceDao;
	
	private MenuDaoI menuDao;
	
	private UserDaoI userDao;

	public UserDaoI getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}

	public ResourceDaoI getResourceDao() {
		return resourceDao;
	}

	@Autowired
	public void setResourceDao(ResourceDaoI resourceDao) {
		this.resourceDao = resourceDao;
	}

	public MenuDaoI getMenuDao() {
		return menuDao;
	}

	@Autowired
	public void setMenuDao(MenuDaoI menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public List<Pmenu> getTreeNode(Pmenu menu) {
		// TODO Auto-generated method stub
		//这里不用TMenu是因为会造成递归效果，TMenu中有Tmenu tmenu;等
		List<Pmenu> nl = new ArrayList<Pmenu>();
		String hql = null;
		if(null==menu.getId()||menu.getId().equals("")){
			//查询所有根节点
			hql="from Menu t where t.menu is null";
		}else{
			//加载当前id下的子节点
			hql="from Menu t where t.menu.id =:id";
		}
		List<Menu> l =menuDao.find(hql,menu);
		if(null!=l && l.size()>0){
			for(Menu t: l){
				Pmenu m= new Pmenu();
				BeanUtils.copyProperties(t, m);
				Set<Menu> set = t.getMenus();
				if(set!=null && !set.isEmpty()){
					m.setState("closed");
				}else{
					m.setState("open");
				}
				nl.add(m);
			}
		}
		
		return nl;
	}

	@Override
	public List<Pmenu> getAllTreeNode(Users users) {
		// TODO Auto-generated method stub
		List<Pmenu> nl = new ArrayList<Pmenu>();
		
		//获取用户可用权限的路径
		Set<Resource> resourceList =new HashSet<Resource>();
		if(null != users){
			for(Role r:users.getRoles()){
				resourceList.addAll(r.getResources());
			}
		}
		
		String urlParams ="(";
		if(null !=resourceList){
			for(Resource resource:resourceList){
				if(null !=resource.getUrl()){
					urlParams+="'"+resource.getUrl()+"',";
				}
				
			}
		}
		
		urlParams+="'')";
		
		//String hql= "from Menu m where m.url in "+urlParams;
		String hql= "from Menu m";
		
		
		//查询对应权限的目录
		List<Menu> l =menuDao.find(hql,resourceList);
		//List<Menu> l =menuDao.find(hql);
		
		if(null!=l && l.size()>0){
			
			//组装数节点
			for(Menu t: l){
				Pmenu m= new Pmenu();
				BeanUtils.copyProperties(t, m);
				Map<String,Object> attributes = new HashMap<String,Object>();
				
				//jsp结尾的才给真实的url,暂时先这么写
				if(t.getUrl().endsWith(".jsp")||t.getUrl().endsWith("admin/sy")){
					attributes.put("url", t.getUrl());
				}
				
				m.setAttributes(attributes);
				Menu tm =t.getMenu();
				if(null!=tm){
					m.setPid(tm.getId());
				}				
				nl.add(m);
			}
		}
		
		return nl;
	}

}
