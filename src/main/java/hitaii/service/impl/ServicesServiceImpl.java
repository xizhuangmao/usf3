package hitaii.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.CompanyDaoI;
import hitaii.dao.NvoccDaoI;
import hitaii.dao.ServicesDaoI;
import hitaii.dao.UserDaoI;
import hitaii.dao.WhesDaoI;
import hitaii.dao.WhesdtlDaoI;
import hitaii.dao.WhesdtlServicesDaoI;
import hitaii.model.Company;
import hitaii.model.Nvocc;
import hitaii.model.Orders;
import hitaii.model.Services;
import hitaii.model.SessionInfo;
import hitaii.model.Users;
import hitaii.model.Whes;
import hitaii.model.Whesdtl;
import hitaii.model.WhesdtlServices;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pservices;
import hitaii.service.ServicesServiceI;
import hitaii.service.WhesdtlServiceI;
import hitaii.util.WhesdtlUtil;

/*
 * @author qc
 * 
 * 时间：20160414
 * 
 * 示例
 * 服务ServiceImpl
 */
@Service("servicesService")
public class ServicesServiceImpl implements ServicesServiceI {
	
	private ServicesDaoI servicesDao;
	private UserDaoI userDao;
	private WhesDaoI whesDao;
	private NvoccDaoI nvoccDao;
	private WhesdtlDaoI whesdtlDao;
	private CompanyDaoI companyDao;
	private WhesdtlServicesDaoI whesdtlServicesDao;
	
	public WhesdtlServicesDaoI getWhesdtlServicesDao() {
		return whesdtlServicesDao;
	}
	@Autowired
	public void setWhesdtlServicesDao(WhesdtlServicesDaoI whesdtlServicesDao) {
		this.whesdtlServicesDao = whesdtlServicesDao;
	}
	public CompanyDaoI getCompanyDao() {
		return companyDao;
	}
	@Autowired
	public void setCompanyDao(CompanyDaoI companyDao) {
		this.companyDao = companyDao;
	}
	public WhesdtlDaoI getWhesdtlDao() {
		return whesdtlDao;
	}
	@Autowired
	public void setWhesdtlDao(WhesdtlDaoI whesdtlDao) {
		this.whesdtlDao = whesdtlDao;
	}
	public NvoccDaoI getNvoccDao() {
		return nvoccDao;
	}
	@Autowired
	public void setNvoccDao(NvoccDaoI nvoccDao) {
		this.nvoccDao = nvoccDao;
	}
	public UserDaoI getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}
	public WhesDaoI getWhesDao() {
		return whesDao;
	}
	@Autowired
	public void setWhesDao(WhesDaoI whesDao) {
		this.whesDao = whesDao;
	}
	public ServicesDaoI getServicesDao() {
		return servicesDao;
	}
	@Autowired
	public void setServicesDao(ServicesDaoI servicesDao) {
		this.servicesDao = servicesDao;
	}

	@Override
	public DataGrid findServices(Pservices pservices) {
		DataGrid d = new DataGrid();
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		String userHql = "from Users where logname = '"+sessionInfo.toString()+"'";
		Users user = userDao.get(userHql);
		String hqlCount = "";
		String hql = null;
		String count = null;
		if(!user.getTypes().equals("1")){
			Iterator<Company> iterator = user.getCompanies().iterator();
			String sql = "(";
			if(iterator.hasNext()){
				while(iterator.hasNext()){
					sql += "'" + iterator.next().getId() + "',";
				}
				sql = sql.substring(0,sql.length()-1);
				sql += ")";
			}else{
				sql += "'')";
			}
			String companyHql = "from Company where id in "+sql+"";
			List<Company> company = companyDao.find(companyHql);
			String companyId = "(";
			if(null != company && company.size() > 0){
				for(int i=0;i<company.size();i++){
					companyId += "'" + company.get(i).getId() + "',";
				}
				companyId = companyId.substring(0,companyId.length()-1);
				companyId += ")";
			}else{
				companyId += "'')";
			}
	
			hql = "select s.id,s.name,s.price,s.note,s.scoreTimes,s.scoreTotle,s.discount,s.active,s.usersId,s.whesId,s.nvoccId,s.ids,s.companyId,s.indate,c.fullname" +
					" from Services as s left join Company as c on s.companyId=c.id where s.companyId in " + companyId;
			count = "select count(*) from Services as s left join Company as c on s.whesId=c.id where s.companyId in " + companyId;
		}else if(user.getTypes().equals("1")){
			hql = "select s.id,s.name,s.price,s.note,s.scoreTimes,s.scoreTotle,s.discount,s.active,s.usersId,s.whesId,s.nvoccId,s.ids,s.companyId,s.indate,c.fullname" +
					" from Services as s left join Company as c on s.companyId=c.id where 1=1 ";
			count = "select count(*) from Services as s left join Company as c on s.whesId=c.id where 1=1 ";
		}
		if(null != pservices.getCompanyId() && !pservices.getCompanyId().isEmpty() && !pservices.getCompanyId().equals("ALL")){
			hqlCount += " and s.companyId = '"+pservices.getCompanyId()+"'";
		}
		if(null != pservices.getName() && !pservices.getName().isEmpty()){
			hqlCount += " and s.name like '%"+pservices.getName()+"%'";
		}
		if(null != pservices.getSort() && !pservices.getSort().isEmpty() && null != pservices.getOrder() && !pservices.getOrder().isEmpty()){
			hqlCount = hqlCount + " order by "+ pservices.getSort() + " " + pservices.getOrder();
		}
		hql += hqlCount;
		
		List<Pservices> services = servicesDao.findwithWhesNvocc(hql, pservices.getPage(), pservices.getRows());
		
		for(Pservices s : services){
			List<WhesdtlServices> whesdtlServices = whesdtlServicesDao.find("from WhesdtlServices where servicesId =:id", s);
			if(null != whesdtlServices && whesdtlServices.size()>0){
				s.setType("1");
			}
		}
		d.setRows(services);
		count += hqlCount;
		d.setTotal(servicesDao.findCount(count));
		return d;
	}

	@Override
	public List<Pservices> findServicesByNvocc(String nvoccId) {
		String hql = "from Services where nvoccId = '"+nvoccId+"'";
		List<Services> services = servicesDao.findServices(hql);
		List<Pservices> pServices = new ArrayList<Pservices>();
		if (null != services && services.size() > 0) {
			for (Services s : services) {
				Pservices pservices = new Pservices();
				BeanUtils.copyProperties(s, pservices);
				pServices.add(pservices);
			}
		}
		return pServices;
	}
	
	@Override
	public String addServices(Pservices pservices) {
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		String userHql = "from Users where logname = '"+sessionInfo.toString()+"'";
		Users user = userDao.get(userHql);
		pservices.setUsersId(user.getId());
		pservices.setId(UUID.randomUUID().toString());
		pservices.setActive("1");
		pservices.setIndate(WhesdtlUtil.getTime());
		
		Services services = new Services();
		BeanUtils.copyProperties(pservices, services);
		servicesDao.saveServices(services);
		return "success";
	}
	
	@Override
	public Json deleteServices(Pservices pservices, Json j) {
		try {
			String hql = "from Services where id = '"+pservices.getId()+"'";
			Services services = servicesDao.getService(hql);
			servicesDao.deleteService(services);
			j.setMsg("success");
			j.setSuccess(true);
			return j;
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			return j;
		}
	}
	
	@Override
	public Pservices findServicesById(Pservices pservices) {
		String hql = "from Services where id = '"+pservices.getId()+"'";
		Services services = servicesDao.getService(hql);
		BeanUtils.copyProperties(services, pservices);
		if(null != pservices.getCompanyId() && !pservices.getCompanyId().isEmpty()){
			String companyHql = "from Company where id = '"+pservices.getCompanyId()+"'";
			Company company = companyDao.get(companyHql);
			if(null != company){
				pservices.setFullname(company.getFullname());
			}
		}
		return pservices;
	}
	
	@Override
	public Pservices updateServices(Pservices pservices) {
		String hql = "from Services where id = '"+pservices.getId()+"'";
		Services services = servicesDao.getService(hql);
		if(null == pservices.getName() || pservices.getName().isEmpty()){
			if(pservices.getActive().equals("1")){
				pservices.setActive("0");
			}else if(pservices.getActive().equals("0")){
				pservices.setActive("1");
			}
		}
		
		hitaii.util.BeanUtils.copyProperties(pservices, services);
		servicesDao.updateService(services);
		BeanUtils.copyProperties(services, pservices);
		return pservices;
	}
	
	@Override
	public DataGrid findServicesByVin(String vin, String whesdtlId, Pservices pservice) {
		DataGrid d = new DataGrid();
		List<WhesdtlServices> whesdtlServices = whesdtlServicesDao.find("from WhesdtlServices where whesdtlId = '"+whesdtlId+"'");
		String sqlservicesID ="";
		if(null!=whesdtlServices){
			sqlservicesID+=" id in ('0000'";
			for(WhesdtlServices whesdtlService : whesdtlServices){
				sqlservicesID+=",'"+whesdtlService.getServices().getId()+"'";
			}
			sqlservicesID+=") ";
		}
		
		String hql = "from Whesdtl where vin = '"+vin+"'";
		Whesdtl whesdtl = whesdtlDao.getPic(hql);
		String serviceHql = " from Services where companyId = '"+whesdtl.getWhesId()+"' and active = '1' or" + sqlservicesID;
		List<Services> services = servicesDao.findServices(serviceHql, pservice.getPage(), pservice.getRows());
		List<Pservices> pServices = new ArrayList<Pservices>();
		if (null != services && services.size() > 0) {
			for (Services s : services) {
				Pservices pservices = new Pservices();
				BeanUtils.copyProperties(s, pservices);
				if(null != s.getPrice() && !s.getPrice().isEmpty() && null != s.getDiscount() && !s.getDiscount().isEmpty()){
					Double pay = (Double.parseDouble(s.getPrice()) * (100-Double.parseDouble(s.getDiscount())))/100;
					java.text.NumberFormat nf = java.text.NumberFormat.getInstance();   
					nf.setGroupingUsed(false);  
					pservices.setPay(nf.format(pay));
				}
				pServices.add(pservices);
			}
		}
		String countServices = "select count(*)" + serviceHql;
		d.setRows(pServices);
		d.setTotal(servicesDao.count(countServices));
		return d;
	}
	
	@Override
	public DataGrid findServicesByCompanyName(String companyId,
			String whesdtlId, Pservices pservices) {
		DataGrid d = new DataGrid();
		String hql = "from Company where id = '"+companyId+"'";
		Company company = companyDao.get(hql);
		
		List<WhesdtlServices> whesdtlServices = whesdtlServicesDao.find("from WhesdtlServices where whesdtlId = '"+whesdtlId+"'");
		String sqlservicesID ="";
		if(null!=whesdtlServices){
			sqlservicesID+=" id in ('0000'";
			for(WhesdtlServices whesdtlService : whesdtlServices){
				sqlservicesID+=",'"+whesdtlService.getServices().getId()+"'";
			}
			sqlservicesID+=") ";
		}
		List<Services> firstServices = servicesDao.findServices("from Services where companyId  = '"+company.getId()+"' and " + sqlservicesID);
		String hqlservicesID ="";
		if(null!=firstServices){
			hqlservicesID+=" id in ('0000'";
			for(Services s : firstServices){
				hqlservicesID+=",'"+s.getId()+"'";
			}
			hqlservicesID+=") ";
		}
		
		if(null != company){
			String serviceHql = " from Services where companyId = '"+company.getId()+"' and active = '1' or" + hqlservicesID;
			List<Services> services = servicesDao.findServices(serviceHql, pservices.getPage(), pservices.getRows());
			List<Pservices> pServices = new ArrayList<Pservices>();
			if (null != services && services.size() > 0) {
				for (Services s : services) {
					Pservices pservice = new Pservices();
					BeanUtils.copyProperties(s, pservice);
					if(null != s.getPrice() && !s.getPrice().isEmpty() && null != s.getDiscount() && !s.getDiscount().isEmpty()){
						Double pay = (Double.parseDouble(s.getPrice()) * (100-Double.parseDouble(s.getDiscount())))/100;
						java.text.NumberFormat nf = java.text.NumberFormat.getInstance();   
						nf.setGroupingUsed(false);  
						pservice.setPay(nf.format(pay));
					}
					pServices.add(pservice);
				}
			}
			String countServices = "select count(*)" + serviceHql;
			d.setRows(pServices);
			d.setTotal(servicesDao.count(countServices));
		}
		return d;
	}
	
	@Override
	public List<Map<String, String>> findWhesAndNvocc() {
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		String userHql = "from Users where logname = '"+sessionInfo.toString()+"'";
		Users user = userDao.get(userHql);
		Iterator<Whes> iterator=user.getWheses().iterator();
		Iterator<Nvocc> iteratorNvocc=user.getNvoccs().iterator();
		String sql = "(";
		if(iterator.hasNext()){
			while(iterator.hasNext()){
				sql += "'" + iterator.next().getId() + "',";
			}
			sql = sql.substring(0,sql.length()-1);
			sql += ")";
		}else{
			sql += "'')";
		}
		String whesHql = "from Whes where id in "+sql+"";
		List<Whes> whes = whesDao.find(whesHql);
		
		String nvoccSql = "(";
		if(iteratorNvocc.hasNext()){
			while(iteratorNvocc.hasNext()){
				nvoccSql += "'" + iteratorNvocc.next().getId() + "',";
			}
			nvoccSql = nvoccSql.substring(0,nvoccSql.length()-1);
			nvoccSql += ")";
		}else{
			nvoccSql += "'')";
		}
		String nvoccHql = "from Nvocc where id in "+nvoccSql+"";
		List<Nvocc> nvocc = nvoccDao.find(nvoccHql);

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (null != whes && whes.size() > 0) {
			for(int i =0;i<whes.size();i++){
				Whes whes1 = whes.get(i);
				Map<String, String> map = new HashMap<String,String>();
				map.put("whesnvocc",  whes1.getFullname());
				map.put("id", whes1.getFullname());
				map.put("type", "whes");
				list.add(map);
			}
			for(int j =0;j<nvocc.size();j++){
				Nvocc nvocc1 = nvocc.get(j);
				Map<String, String> map = new HashMap<String,String>();
				map.put("whesnvocc",  nvocc1.getFullname());
				map.put("id", nvocc1.getFullname());
				map.put("type", "nvocc");
				list.add(map);
			}
		}
		return list;
	}
	
	@Override
	public DataGrid findServicesBywhesnvocc(Pservices pservices) {
		DataGrid d = new DataGrid();
		List<Services> services = new ArrayList<Services>();
		if(null != pservices.getName() && !pservices.getName().isEmpty()){
			services = servicesDao.findServices(("from Services where companyId='"+pservices.getCompanyId()+"' and name like '%"+pservices.getName()+"%' order by "+pservices.getSort()+" "+pservices.getOrder()+""), pservices.getPage(), pservices.getRows());
			d.setTotal(servicesDao.count("select count(*) from Services where companyId='"+pservices.getCompanyId()+"' and name like '%"+pservices.getName()+"%'"));
		}else{
			services = servicesDao.findServices("from Services where companyId='"+pservices.getCompanyId()+"' order by "+pservices.getSort()+" "+pservices.getOrder()+"", pservices.getPage(), pservices.getRows());
			d.setTotal(servicesDao.count("select count(*) from Services where companyId='"+pservices.getCompanyId()+"'"));
		}
		d.setRows(services);
		return d;
	}
}
