package hitaii.service.impl;

import hitaii.dao.CarrierDaoI;
import hitaii.dao.NvoccDaoI;
import hitaii.dao.RoleDaoI;
import hitaii.dao.CompanyDaoI;
import hitaii.dao.TruckDaoI;
import hitaii.dao.UserDaoI;
import hitaii.dao.WhesDaoI;
import hitaii.model.Carrier;
import hitaii.model.Company;
import hitaii.model.Nvocc;
import hitaii.model.Truck;
import hitaii.model.Users;
import hitaii.model.Role;
import hitaii.model.Whes;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Puser;
import hitaii.pageModel.Pwhesdtl;
import hitaii.service.UserServiceI;
import hitaii.util.BeanUtils;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.UsfException;


@Service("userService")
public class UserServiceImpl implements UserServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(UserServiceImpl.class);

	private UserDaoI userDao;
	
	private RoleDaoI roleDao;
	
	private CompanyDaoI companyDao;
	
	private WhesDaoI whesDao;
	
	private NvoccDaoI nvoccDao;
	
	private CarrierDaoI carrierDao;
	
	private TruckDaoI truckDao;

	public WhesDaoI getWhesDao() {
		return whesDao;
	}

	@Autowired
	public void setWhesDao(WhesDaoI whesDao) {
		this.whesDao = whesDao;
	}

	public NvoccDaoI getNvoccDao() {
		return nvoccDao;
	}

	@Autowired
	public void setNvoccDao(NvoccDaoI nvoccDao) {
		this.nvoccDao = nvoccDao;
	}

	public CarrierDaoI getCarrierDao() {
		return carrierDao;
	}

	@Autowired
	public void setCarrierDao(CarrierDaoI carrierDao) {
		this.carrierDao = carrierDao;
	}

	public TruckDaoI getTruckDao() {
		return truckDao;
	}

	@Autowired
	public void setTruckDao(TruckDaoI truckDao) {
		this.truckDao = truckDao;
	}

	public RoleDaoI getRoleDao() {
		return roleDao;
	}

	@Autowired
	public void setCompanyDao(CompanyDaoI companyDao) {
		this.companyDao = companyDao;
	}
	
	public CompanyDaoI getCompanyDao() {
		return companyDao;
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
	public Puser save(Puser puser) throws Exception {
		Users t = new Users();

		BeanUtils.copyProperties(puser, t, new String[] { "password" });
		t.setId(UUID.randomUUID().toString());
		t.setPassword(puser.getPassword());
		Users fromDao = userDao.get("from Users t where t.logname=:logname", t);
		
		if (null != fromDao) {			
			UsfException e =new UsfException("logname already exists,please change logname!");			
			throw e;
		}else{
			userDao.save(t);			
		} 
		
		BeanUtils.copyProperties(t,puser);
		
		return(puser);
	}

	@Override
	public Users login(Puser puser) {

		//puser.setPwd(MD5Util.md5(puser.getPwd()));
		Users t = userDao.get("from Users t where t.logname=:logname and t.password=:password", puser);
		if (null != t) {
			return t;
		}
		return null;
	}

	@Override
	public DataGrid dataGrid(Puser puser) {

		DataGrid d = new DataGrid();
		List<Users> usersList = new ArrayList<Users>(); 
		List<Puser> pusersList = new ArrayList<Puser>(); 
		String hql ="from Users t ";
		hql = addWhere(puser, hql);
		
		hql = addOrder(puser, hql);
		
		usersList = userDao.find(hql,puser,puser.getPage(), puser.getRows());
		
	//	long startTime=System.currentTimeMillis();   //获取开始时间
		changeModel(usersList, pusersList);
	//	long endTime=System.currentTimeMillis(); //获取结束时间
	//	System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
		d.setRows(pusersList);
		d.setTotal(userDao.count("select count(*)"+hql,puser));
		return d;
	}

	private void changeModel(List<Users> tl, List<Puser> ul) {
		if(null!=tl && tl.size()>0){
			for(Users t: tl){
				Puser u = new Puser();
				//基本属性
				u.setId(t.getId());
				u.setLogname(t.getLogname());
				u.setActive(t.getActive());
				u.setPassword(t.getPassword());
				u.setDatein(t.getDatein());
				//集合
				Hibernate.initialize(t.getUserses());
				Hibernate.initialize(t.getRoles());
				Hibernate.initialize(t.getCompanies());
				Hibernate.initialize(t.getWheses());
				Hibernate.initialize(t.getNvoccs());
				Hibernate.initialize(t.getCarriers());
				Hibernate.initialize(t.getTrucks());
				u.setUserses(t.getUserses());
				u.setCompanies(t.getCompanies());
				u.setWheses(t.getWheses());
				u.setNvoccs(t.getNvoccs());
				u.setCarriers(t.getCarriers());
				u.setTrucks(t.getTrucks());
				u.setRoles(t.getRoles());
				
				ul.add(u);
					
			}	
		}
	}

	private String addOrder(Puser puser, String hql) {
		if(null!=puser.getSort()){
			hql = hql+" order by "+puser.getSort();
			if(null!=puser.getOrder()){
				hql=hql+" "+puser.getOrder();
			}
		}
		return hql;
	}

	private String addWhere(Puser puser, String hql) {
		hql += " where 1=1 ";
		if(null!=puser.getLogname()&&!puser.getLogname().trim().equals("")){
			hql +=" and logname like :logname ";
			puser.setLogname("%%"+puser.getLogname()+"%%");
		}

		return hql;
	}

	@Override
	public void remove(Puser puser) {
		
		if(!StringUtils.isBlank(puser.getIds())){
			for (String id:puser.getIds().split(",")){
				Users tuser = userDao.get(Users.class, id);
				if(null!=tuser){
					userDao.delete(tuser);
				}
				
			}
		}
			
	}

	@Override
	public Puser edit(Puser puser) throws Exception{
		
		Users user = getUsers(puser);
			
		BeanUtils.copyProperties(puser, user);
	
		return(puser);
	}

	@Override
	public Puser get(Puser puser) throws Exception{

		Users user = getUsers(puser);
		
		
		Hibernate.initialize(user.getRoles());
		Hibernate.initialize(user.getCompanies());
		Hibernate.initialize(user.getWheses());
		Hibernate.initialize(user.getNvoccs());
		Hibernate.initialize(user.getCarriers());
		Hibernate.initialize(user.getTrucks());
		BeanUtils.copyProperties(user, puser);
	
//		String roleids ="";
//		
//		if( null!=t.getRoles()){
//			for(Role role:t.getRoles()){
//				roleids+=role.getId()+",";
//			}
//		}
//		
//		puser.setRoleids(roleids);
		
		return puser;
	}

	@Override
	public Puser editRole(Puser puser) throws Exception{
		
		//先获取用户
		Users user = getUsers(puser);
//		BeanUtils.copyProperties(tuser, puser);
//		
//		tuser.getRoles().clear();
		if(null!=user){
			user.setRoles(new HashSet<Role>());
		}
		
		//再修改角色
		if(!StringUtils.isBlank(puser.getRoleids())){
			
			for(String troleId:puser.getRoleids().split(",")){
				if(!StringUtils.isBlank(troleId)){
					Role role = roleDao.get(Role.class, troleId);
					user.getRoles().add(role);
				}
			}
		}
		BeanUtils.copyProperties(user, puser);
		
		return puser;
	}
	
	
	
	@Override
	public Puser editCompany(Puser puser) throws Exception {
		
		try {
			// 先获取用户
			Users user = getUsers(puser);
			if(null!=user){
				user.setCompanies(new HashSet<Company>());
			}
			
			// 修改公司
			if (!StringUtils.isBlank(puser.getCompanyids())) {

				for (String companyId : puser.getCompanyids().split(",")) {
					if (!StringUtils.isBlank(companyId)) {
						Company company = companyDao.get(Company.class, companyId);
						user.getCompanies().add(company);
					}
				}
			}
			BeanUtils.copyProperties(user, puser);
		} catch (Exception e) {
			throw e;
		}
		
		
		return puser;
	}
	
	@Override
	public Puser editWhes(Puser puser) throws Exception {
		
		try {
			// 先获取用户
			Users user = getUsers(puser);
			if(null!=user){
				user.setWheses(new HashSet<Whes>());
			}
			
			// 修改仓库
			if (!StringUtils.isBlank(puser.getWhesids())) {

				for (String whesId : puser.getWhesids().split(",")) {
					if (!StringUtils.isBlank(whesId)) {
						Whes whes = whesDao.get(Whes.class, whesId);
						user.getWheses().add(whes);
					}
				}
			}
			BeanUtils.copyProperties(user, puser);
		} catch (Exception e) {
			throw e;
		}
		
		
		return puser;
	}

	@Override
	public Puser editNvocc(Puser puser) throws Exception{

		// 先获取用户
		Users user = getUsers(puser);
		if(null!=user){
			user.setNvoccs(new HashSet<Nvocc>());
		}
		// 修改nvocc
		if (!StringUtils.isBlank(puser.getNvoccids())) {
			
			for (String nvoccId : puser.getNvoccids().split(",")) {
				if (!StringUtils.isBlank(nvoccId)) {
					Nvocc nvocc = nvoccDao.get(Nvocc.class, nvoccId);
					user.getNvoccs().add(nvocc);
				}
			}
		}
		BeanUtils.copyProperties(user, puser);
		return puser;
	}

	@Override
	public Puser editCarrier(Puser puser) throws Exception{

		// 先获取用户
		Users user = getUsers(puser);
		if(null!=user){
			user.setCarriers(new HashSet<Carrier>());
		}
		// 修改航运公司
		if (!StringUtils.isBlank(puser.getCarrierids())) {
			
			for (String carrierId : puser.getCarrierids().split(",")) {
				if (!StringUtils.isBlank(carrierId)) {
					Carrier carrier = carrierDao.get(Carrier.class, carrierId);
					user.getCarriers().add(carrier);
				}
			}
		}
		BeanUtils.copyProperties(user, puser);
		return puser;
	}

	@Override
	public Puser editTruck(Puser puser) throws Exception{

		// 先获取用户
		Users user = getUsers(puser);
		if(null!=user){
			user.setTrucks(new HashSet<Truck>());
		}
		
		// 修改卡车公司
		if (!StringUtils.isBlank(puser.getTruckids())) {
			
			for (String truckId : puser.getTruckids().split(",")) {
				if (!StringUtils.isBlank(truckId)) {
					Truck truck = truckDao.get(Truck.class, truckId);
					user.getTrucks().add(truck);
				}
			}
		}
		BeanUtils.copyProperties(user, puser);
		
		return puser;
	}
	

	//根据id或者logname获取用户信息
	private Users getUsers(Puser puser) throws Exception{
		Users user = new Users();
		if(null!=puser.getId()&&puser.getId().length()>0){
			user=userDao.get(Users.class, puser.getId());
		}else if(null!=puser.getLogname()&&puser.getLogname().length()>0&&!puser.getLogname().equals("undefined")){
			user=userDao.get("from Users where logname =:logname",puser);
		}else{
			UsfException e =new UsfException("logname is null!please contact admin!");			
			throw e;
		}
		return user;
	}

	@Override
	public Users getCustomer(Pwhesdtl pwhesdtl) {
		return userDao.get("from Users where id =:usersId",pwhesdtl);
	}

	@Override
	public List<Users> getCustomerName() {
		String hql = "from Users where types = '2' order by fullname";
		return userDao.find(hql);
	}
}
