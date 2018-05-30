package hitaii.service.impl;

import java.util.ArrayList;
import java.util.List;

import hitaii.dao.CountryDaoI;
import hitaii.model.Country;
import hitaii.model.Make;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pcountry;
import hitaii.service.CountryServiceI;
import hitaii.util.WhesdtlUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("countryService")
public class CountryServiceImpl implements CountryServiceI{
	
	private CountryDaoI countryDao;
	public CountryDaoI getCountryDao() {
		return countryDao;
	}
	@Autowired
	public void setCountryDao(CountryDaoI countryDao) {
		this.countryDao = countryDao;
	}
	@Override
	public List<Pcountry> findCountryName() {
		String hql = "from Country order by country";
		List<Country> countrys =countryDao.find(hql);
		List<Pcountry> pcountries = new ArrayList<Pcountry>();
		if (null != countrys && countrys.size() > 0) {
			for (Country c : countrys) {
				Pcountry pcountry = new Pcountry();
				BeanUtils.copyProperties(c, pcountry);
				pcountries.add(pcountry);
			}
		}
		return pcountries;
	}
	@Override
	public DataGrid getCountryDatagrid(Pcountry country) {
		DataGrid d = new DataGrid();
		List<Pcountry> pcountrys = new ArrayList<Pcountry>();
		String hql = "from Country";
		if(null != country.getSort() && !country.getSort().isEmpty() && null != country.getOrder() && !country.getOrder().isEmpty()){
			hql=hql+" order by "+ country.getSort() + " " + country.getOrder();
		}
		
		List<Country> countrys=countryDao.find(hql, country.getPage(), country.getRows());
		changeModelTd(countrys,pcountrys);
		d.setRows(pcountrys);
		
		d.setTotal(countryDao.count("select count(*)"+hql,country));
		return d;
	}
	
	private void changeModelTd(List<Country> countrys, List<Pcountry> pcountrys) {
		if (null != countrys && countrys.size() > 0) {
			for (Country c : countrys) {
				Pcountry p = new Pcountry();
				BeanUtils.copyProperties(c, p);
				pcountrys.add(p);
			}
		}
	}
	
	public boolean saveOneCountry(Pcountry country){
		if(null==country.getCountry()||"".equals(country.getCountry())){
			return false;
		}
		if(null==country.getShortname()||"".equals(country.getShortname())){
			return false;
		}
		country.setId(WhesdtlUtil.getUUID());
		Country c = new Country();
		BeanUtils.copyProperties(country, c);
		countryDao.save(c);
		return true;
	}
	@Override
	public Json findCountryById(Pcountry pcountry) {
		Json j = new Json();
		Country country = countryDao.get("from Country where id =:id ", pcountry);
		if(null != country){
			BeanUtils.copyProperties(country, pcountry);
			j.setObj(pcountry);
			j.setSuccess(true);
		}else{
			j.setMsg("country is not exist");
		}
		return j;
		
	}
	
	@Override
	public Json addCountry(Pcountry pcountry) {
		Json j = new Json();
		try {
			pcountry.setId(WhesdtlUtil.getUUID());
			Country country = new Country();
			BeanUtils.copyProperties(pcountry, country);
			countryDao.save(country);
			j.setMsg("success");
			j.setSuccess(true);
		} catch (BeansException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@Override
	public boolean deleteCountry(Pcountry pcountry) {
		if("".equals(pcountry.getId()) || null == pcountry.getId()){
			return false;
		}
		Country country=new Country();
		BeanUtils.copyProperties(pcountry, country);
		countryDao.delete(country);
		return true;
	}
	
	@Override
	public Json editCountry(Pcountry pcountry) {
		Json j = new Json();
		Country country = countryDao.get("from Country where id=:id", pcountry);
		if(null != country){
			hitaii.util.BeanUtils.copyProperties(pcountry, country);
			countryDao.update(country);
			j.setMsg("success");
			j.setSuccess(true);
		}else{
			j.setMsg("country is not exist");
		}
		return j;
	}
}
