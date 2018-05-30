package hitaii.service.impl;

import hitaii.dao.ResourceDaoI;
import hitaii.dao.RoleDaoI;
import hitaii.dao.UserDaoI;
import hitaii.model.Resource;
import hitaii.model.Role;
import hitaii.model.Users;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Prole;
import hitaii.service.RoleServiceI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleServiceI {

	private static final Logger logger = Logger
			.getLogger(RoleServiceImpl.class);

	private RoleDaoI roleDao;
	
	private UserDaoI userDao;

	private ResourceDaoI resourceDao;

	public ResourceDaoI getResourceDao() {
		return resourceDao;
	}

	@Autowired
	public void setResourceDao(ResourceDaoI resourceDao) {
		this.resourceDao = resourceDao;
	}

	public RoleDaoI getRoleDao() {
		return roleDao;
	}

	@Autowired
	public void setRoleDao(RoleDaoI roleDao) {
		this.roleDao = roleDao;
	}
	
	public UserDaoI getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}

	@Override
	public DataGrid datagrid(Prole prole) {
		DataGrid d = new DataGrid();
		List<Role> tl = new ArrayList<Role>(); 
		List<Prole> ul = new ArrayList<Prole>(); 
		String hql ="from Role t ";
		hql = addWhere(prole, hql);
		
		hql = addOrder(prole, hql);
		
		tl = roleDao.find(hql,prole,prole.getPage(), prole.getRows());
		
		changeModel(tl, ul);
		
		d.setRows(ul);
		d.setTotal(roleDao.count("select count(*)"+hql,prole));
		return d;
	}
	
	@Override
	public DataGrid userrolesdatagrid(Prole prole) {
		DataGrid d = new DataGrid();
		//全部角色
		List<Role> roleList = new ArrayList<Role>(); 
		List<Prole> proleList = new ArrayList<Prole>(); 
		String hql ="from Role t ";
		hql = addWhere(prole, hql);
		
		hql = addOrder(prole, hql);
		
		roleList = roleDao.find(hql,prole,prole.getPage(), prole.getRows());
		
		changeModel(roleList, proleList);
		
		//查询该用户的角色，并把返回的角色list打勾
		List<Users> usersList = userDao.find("from Users where id="+prole.getUserid());
		Users user = new Users();
		List<Role> userroles = new ArrayList<Role>();
		if(usersList.size()==1){
			user =usersList.get(0);
			Hibernate.initialize(user.getRoles());
			
			for(Role role:user.getRoles()){
				role.getId();
			}
		}
		
		d.setRows(proleList);
		d.setTotal(roleDao.count("select count(*)"+hql,prole));
		return d;
	}
	
	private void changeModel(List<Role> tl, List<Prole> ul) {
		if(null!=tl && tl.size()>0){
			for(Role t: tl){
				Prole u = new Prole();
				BeanUtils.copyProperties(t, u);
				ul.add(u);
			}
			
		}
	}

	private String addOrder(Prole prole, String hql) {
		if(null!=prole.getSort()){
			hql = hql+" order by "+prole.getSort();
			if(null!=prole.getOrder()){
				hql=hql+" "+prole.getOrder();
			}
		}
		return hql;
	}

	private String addWhere(Prole prole, String hql) {
		hql +=" where 1=1 ";
		if(null!=prole.getName()&&!prole.getName().trim().equals("")){
			hql +=" and name like :name ";
			prole.setName("%%"+prole.getName()+"%%");
		}
		return hql;
	}

	@Override
	public void add(Prole prole) {
		Role trole = new Role();
		
		BeanUtils.copyProperties(prole, trole);
		
		trole.setId(UUID.randomUUID().toString());
		//先保存角色
		roleDao.save(trole);
		//再做角色和资源的关系
		if(!StringUtils.isBlank(prole.getIds())){
			
			trole.setResources(new HashSet<Resource>());
			
			for(String resourceId : prole.getIds().split(",")){
				if (!StringUtils.isBlank(resourceId)) {
					Resource resource  = resourceDao.get(Resource.class, resourceId);
					if(null != resource){
						trole.getResources().add(resource);
					}
				}
			}
		}
		
		
	}

	@Override
	public void delete(Prole prole) {

		Role trole  = roleDao.get(Role.class, prole.getId());
		
		if(null!=trole){
			roleDao.delete(trole);
		}
		
		
	}

	@Override
	public void update(Prole prole) {
	
		//先获取角色
		Role trole  = roleDao.get(Role.class, prole.getId());
		
		BeanUtils.copyProperties(prole, trole);
		//再做角色和资源关系的更新
		
		trole.getResources().clear();
		
		if(!StringUtils.isBlank(prole.getIds())){
			
			trole.setResources(new HashSet<Resource>());
			
			for(String resourceId : prole.getIds().split(",")){
				if (!StringUtils.isBlank(resourceId)) {
					Resource resource  = resourceDao.get(Resource.class, resourceId);
					if(null != resource){
						trole.getResources().add(resource);
					}
				}
			}
		}
		
	}

	@Override
	public void roleresource(Prole prole) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Prole get(Prole prole) {
		
		Role trole = roleDao.get(Role.class, prole.getId());
		
		BeanUtils.copyProperties(trole, prole);
		
		String ids ="";
		
		if(null != trole.getResources()){
			for (Resource resource :trole.getResources()){
				ids += resource.getId()+",";
			}
		}
		
		prole.setIds(ids);
					
		return prole;
	}

	
}
