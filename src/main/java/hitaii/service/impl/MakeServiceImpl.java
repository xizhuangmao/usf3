package hitaii.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.CountryDaoI;
import hitaii.dao.MakeDaoI;
import hitaii.model.Country;
import hitaii.model.Make;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pmake;
import hitaii.service.MakeServiceI;
import hitaii.util.WhesdtlUtil;

@Service(value="makeService")
public class MakeServiceImpl implements MakeServiceI {
	
	private MakeDaoI makeDao;
	private CountryDaoI countryDao;
	
	public CountryDaoI getCountryDao() {
		return countryDao;
	}
	@Autowired
	public void setCountryDao(CountryDaoI countryDao) {
		this.countryDao = countryDao;
	}
	public MakeDaoI getMakeDao() {
		return makeDao;
	}
	@Autowired
	public void setMakeDao(MakeDaoI makeDao) {
		this.makeDao = makeDao;
	}

	@Override
	public List<Pmake> findAllMake() {
		String hql = "from Make order by make";
		List<Make> makes = makeDao.find(hql);
		List<Pmake> pmakes = new ArrayList<Pmake>();
		if (null != makes && makes.size() > 0) {
			for (Make m : makes) {
				Pmake pmake = new Pmake();
				BeanUtils.copyProperties(m, pmake);
				pmakes.add(pmake);
			}
		}
		return pmakes;
	}
	
	@Override
	public DataGrid getAllMakeDataGrid(Pmake pmake) {
		DataGrid d = new DataGrid();
		List<Pmake> pmakes = new ArrayList<Pmake>();
		String hql = "from Make where 1=1 ";
		List<Make> makes= new ArrayList<Make>();
		if(null==pmake.getCountryId() || "".equals(pmake.getCountryId())){
			if(null != pmake.getSort() && !pmake.getSort().isEmpty() && null != pmake.getOrder() && !pmake.getOrder().isEmpty()){
				hql=hql+" order by "+ pmake.getSort() + " " + pmake.getOrder();
			}
			makes = makeDao.find(hql,pmake.getPage(),pmake.getRows());
		}else{
			hql = hql + " and countryId=:countryId";
			if(null != pmake.getSort() && !pmake.getSort().isEmpty() && null != pmake.getOrder() && !pmake.getOrder().isEmpty()){
				hql=hql+" order by "+ pmake.getSort() + " " + pmake.getOrder();
			}
			makes = makeDao.find(hql,pmake,pmake.getPage(),pmake.getRows());
		}
		changeModelTd(makes,pmakes);
		d.setRows(pmakes);
		d.setTotal(makeDao.count("select count(*)"+hql,pmake));
		return d;
	}
	
	private void changeModelTd(List<Make> makes, List<Pmake> pmakes) {
		for(int i=0;i<makes.size();i++){
			Hibernate.initialize(makes.get(i).getCountry());
			Pmake p = new Pmake();
			BeanUtils.copyProperties(makes.get(i), p);
			Country c= makes.get(i).getCountry();
			if(null!=c){
				p.setCountryId(c.getId());
				p.setCountryName(c.getCountry());
			}
			pmakes.add(p);
		}
	}
	
	@Override
	public Json addMake(Pmake pmake) {
		Json j = new Json();
		try {
			pmake.setId(WhesdtlUtil.getUUID());
			Make make = new Make();
			Country country = countryDao.get("from Country where id =:countryId", pmake);
			pmake.setCountry(country);
			BeanUtils.copyProperties(pmake, make);
			makeDao.save(make);
			j.setMsg("success");
			j.setSuccess(true);
		} catch (BeansException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@Override
	public List<Pmake> findMakeName() {
		String hql = "from Make order by make";
		List<Make> makes = makeDao.find(hql);
		List<Pmake> pmakes =new ArrayList<Pmake>();
		if(null != makes && makes.size() > 0){
			for(Make m : makes){
				Pmake pmake = new Pmake();
				BeanUtils.copyProperties(m, pmake);
				pmakes.add(pmake);
			}
		}
		return pmakes;
	}
	
	@Override
	public Json findMakeById(Pmake pmake) {
		Json j = new Json();
		Make make = makeDao.get("from Make where id = :id", pmake);
		if(null != make){
			BeanUtils.copyProperties(make, pmake);
			j.setObj(pmake);
			j.setSuccess(true);
		}else{
			j.setMsg("make is not exist");
		}
		return j;
	}
	@Override
	public boolean deleteMake(Pmake pmake) {
		if("".equals(pmake.getId()) || null == pmake.getId()){
			return false;
		}
		Make make=new Make();
		BeanUtils.copyProperties(pmake, make);
		makeDao.delete(make);
		return true;
	}
	
	@Override
	public Json editMake(Pmake pmake) {
		Json j = new Json();
		Make make = makeDao.get("from Make where id=:id", pmake);
		if(null != make){
			Country country = countryDao.get("from Country where id =:countryId", pmake);
			pmake.setCountry(country);
			hitaii.util.BeanUtils.copyProperties(pmake, make);
			makeDao.update(make);
			j.setMsg("success");
			j.setSuccess(true);
		}else{
			j.setMsg("make is not exist");
		}
		return j;
	}

}
