package hitaii.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.CityDaoI;
import hitaii.dao.CompanyDaoI;
import hitaii.dao.CountryDaoI;
import hitaii.dao.StateDaoI;
import hitaii.dao.UserDaoI;
import hitaii.model.City;
import hitaii.model.Company;
import hitaii.model.Country;
import hitaii.model.SessionInfo;
import hitaii.model.State;
import hitaii.model.Users;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pcompany;
import hitaii.service.CompanyServiceI;

@Service("companyService")
public class CompanyServiceImpl implements CompanyServiceI {
	
	private CompanyDaoI companyDao;
	private CountryDaoI countryDao;
	private StateDaoI stateDao;
	private CityDaoI cityDao;
	private UserDaoI userDao;
	
	public UserDaoI getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}
	public CountryDaoI getCountryDao() {
		return countryDao;
	}
	@Autowired
	public void setCountryDao(CountryDaoI countryDao) {
		this.countryDao = countryDao;
	}

	public StateDaoI getStateDao() {
		return stateDao;
	}
	@Autowired
	public void setStateDao(StateDaoI stateDao) {
		this.stateDao = stateDao;
	}

	public CityDaoI getCityDao() {
		return cityDao;
	}
	@Autowired
	public void setCityDao(CityDaoI cityDao) {
		this.cityDao = cityDao;
	}

	public CompanyDaoI getCompanyDao() {
		return companyDao;
	}
	
	@Autowired
	public void setCompanyDao(CompanyDaoI companyDao) {
		this.companyDao = companyDao;
	}

	@Override
	public List<Pcompany> findAllCompanies() {
		String hql = "from Company order by fullname";
		List<Company> companies = companyDao.find(hql);
		List<Pcompany> pcompanies = new ArrayList<Pcompany>();
		changeModelTd(companies,pcompanies);
		return pcompanies;
	}

	@Override
	public DataGrid getAllCompanyDataGrid(Pcompany pcompany) {
		DataGrid d = new DataGrid();
		List<Pcompany> pcompanies = new ArrayList<Pcompany>();
		String hql = "from Company where 1=1 ";
		List<Company> companies= new ArrayList<Company>();
			if(null != pcompany.getSort() && !pcompany.getSort().isEmpty() && null != pcompany.getOrder() && !pcompany.getOrder().isEmpty()){
				hql=hql+" order by "+ pcompany.getSort() + " " + pcompany.getOrder();
			}
			companies = companyDao.find(hql,pcompany.getPage(),pcompany.getRows());
			
		changeModelTd(companies,pcompanies);
		d.setRows(pcompanies);
		d.setTotal(companyDao.count("select count(*)"+hql,pcompany));
		return d;
	}
	
	private void changeModelTd(List<Company> companies, List<Pcompany> pcompanies) {
		if(companies.size()>0){
			for(Company company : companies){
				Pcompany pcompany = new Pcompany();
				BeanUtils.copyProperties(company, pcompany);
				pcompanies.add(pcompany);
			}
		}
	}
	
	@Override
	public List<Pcompany> findWhescompany() {
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		String hql;
		if(sessionInfo.getUsers().getTypes().equals("1")){
			hql = "from Company where types = '1'";
		}else{
			Set<Company> companies = sessionInfo.getUsers().getCompanies();
			String companyId = "";
			if(null != companies){
				companyId = " ('oooo'";
				for(Company company : companies){
					companyId += ",'"+company.getId()+"'";
				}
				companyId += ")";
			}
			hql = "from Company where types = '1' and id in " + companyId;
		}

		List<Company> whesCompany = companyDao.find(hql);
		List<Pcompany> pwhesCompany = new ArrayList<Pcompany>();
		if (null != whesCompany && whesCompany.size() > 0) {
			for (Company c : whesCompany) {
				Pcompany pwhes = new Pcompany();
				BeanUtils.copyProperties(c, pwhes);
				pwhesCompany.add(pwhes);
			}
		}
		return pwhesCompany;
	}
	
	@Override
	public List<Pcompany> findPrealertWhescompany() {	
		String hql = "from Company where types = '1'";
		List<Company> whesCompany = companyDao.find(hql);
		List<Pcompany> pwhesCompany = new ArrayList<Pcompany>();
		if (null != whesCompany && whesCompany.size() > 0) {
			for (Company c : whesCompany) {
				Pcompany pwhes = new Pcompany();
				BeanUtils.copyProperties(c, pwhes);
				pwhesCompany.add(pwhes);
			}
		}
		return pwhesCompany;
	}

	@Override
	public Json deleteCompanyById(Pcompany pcompany) {
		Json j = new Json();
		Company company = companyDao.get("from Company where id =:id", pcompany);
		if(null != company){
			try {
				companyDao.delete(company);
				j.setMsg("success");
				j.setSuccess(true);
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
		}
		return j;
	}

	@Override
	public Json addCompany(Pcompany pcompany) {
		Json j = new Json();
		try {
			pcompany.setId(UUID.randomUUID().toString());
			pcompany.setActive("0");
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
			String time = format.format(date); 
			pcompany.setDatein(time);
			if(null != pcompany.getCountryId() && !pcompany.getCountryId().isEmpty()){
				Country country = countryDao.get("from Country where id =:countryId", pcompany);
				pcompany.setCountry(country.getCountry());
			}
			if(null != pcompany.getStateId() && !pcompany.getStateId().isEmpty()){
				State state = stateDao.get("from State where id =:stateId", pcompany);
				pcompany.setState(state.getState());
			}
			if(null != pcompany.getCityId() && !pcompany.getCityId().isEmpty()){
				City city = cityDao.get("from City where id=:cityId", pcompany);
				pcompany.setCity(city.getCity());
			}
			Company company = new Company();
			BeanUtils.copyProperties(pcompany, company);
			companyDao.save(company);
			j.setMsg("success");
			j.setSuccess(true);
		} catch (BeansException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@Override
	public Json findCompanyById(Pcompany pcompany) {
		Json j = new Json();
		Company company = companyDao.get("from Company where id =:id", pcompany);
		if(null != company){
			if(null != company.getCountry() && !company.getCountry().isEmpty()){
				Country country = countryDao.get("from Country where country =:country", company);
				pcompany.setCountryId(country.getId());
			}
			if(null != company.getState() && !company.getState().isEmpty()){
				State state = stateDao.get("from State where state =:state", company);
				pcompany.setStateId(state.getId());
			}
			if(null != company.getCity() && !company.getCity().isEmpty()){
				City city = cityDao.get("from City where city=:city", company);
				pcompany.setCityId(city.getId());
			}
			hitaii.util.BeanUtils.copyProperties(company, pcompany);
			j.setMsg("success");
			j.setSuccess(true);
			j.setObj(pcompany);
		}else{
			j.setMsg("Company is not exit");
		}
		return j;
	}
	
	@Override
	public Json updateCompany(Pcompany pcompany) {
		Json j = new Json();
		Company company = companyDao.get("from Company where id =:id", pcompany);
		if(null != company){
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
			String time = format.format(date); 
			pcompany.setDateupdate(time);
			hitaii.util.BeanUtils.copyProperties(pcompany, company);
			companyDao.update(company);
			j.setMsg("success");
			j.setSuccess(true);
		}else{
			j.setMsg("Company is not exit");
		}
		return j;
	}
	
	@Override
	public List<Company> findCompany() {
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		String userHql = "from Users where logname = '"+sessionInfo.toString()+"'";
		Users user = userDao.get(userHql);
		String hql = null;
		if(!user.getTypes().equals("1")){
			Iterator<Company> iterator = user.getCompanies().iterator();
			String companyId = "(";
			if(iterator.hasNext()){
				while(iterator.hasNext()){
					companyId += "'" + iterator.next().getId() + "',";
				}
				companyId = companyId.substring(0,companyId.length()-1);
				companyId += ")";
			}else{
				companyId += "'')";
			}
			
			hql = "from Company where id in " + companyId + " order by fullname";
		}else if(user.getTypes().equals("1")){
			hql = "from Company order by fullname";
		}
		List<Company> companies = companyDao.find(hql);
		return companies;
	}
	
	@Override
	public List<Company> findAllWhes() {
		return companyDao.find("from Company where types = '1'");
	}
}
