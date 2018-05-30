package hitaii.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.CityDaoI;
import hitaii.dao.StateDaoI;
import hitaii.model.City;
import hitaii.model.Make;
import hitaii.model.Model;
import hitaii.model.State;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pcity;
import hitaii.pageModel.Pmake;
import hitaii.service.CityServiceI;
import hitaii.util.WhesdtlUtil;

@Service("cityService")
public class CityServiceImpl implements CityServiceI {
	private CityDaoI cityDao;
	private StateDaoI stateDao;
	public CityDaoI getCityDao() {
		return cityDao;
	}
	@Autowired
	public void setCityDao(CityDaoI cityDao) {
		this.cityDao = cityDao;
	}
	public StateDaoI getStateDao() {
		return stateDao;
	}
	@Autowired
	public void setStateDao(StateDaoI stateDao) {
		this.stateDao = stateDao;
	}
	
	@Override
	public List<Pcity> findCityName() {
		List<City> cities = cityDao.find("from City order by city");
		List<Pcity> pcities = new ArrayList<Pcity>();
		if (null != cities && cities.size() > 0) {
			for (City c : cities) {
				Pcity pcity = new Pcity();
				BeanUtils.copyProperties(c, pcity, new String[]{"state"});
				pcities.add(pcity);
			}
		}
		return pcities;
	}
	
	@Override
	public DataGrid getCityDataGrid(Pcity pcity) {
		DataGrid d = new DataGrid();
		String hql = "select city.*, state.state from City as city left join State as state on city.stateId = state.id where 1=1 ";
		String count = "select count(*) from City as city left join State as state on city.stateId = state.id where 1=1 ";
		
		if(null != pcity.getStateId() && !pcity.getStateId().isEmpty() && !pcity.getStateId().equals("ALL")){
			hql = hql + " and city.stateId = '"+pcity.getStateId()+"'";
			count = count + " and city.stateId = '"+pcity.getStateId()+"'";
		}
		if(null != pcity.getCityId() && !pcity.getCityId().isEmpty() && !pcity.getCityId().equals("ALL")){
			hql = hql + " and city.id = '"+pcity.getCityId()+"'";
			count = count + " and city.id = '"+pcity.getCityId()+"'";
		}
		if(null != pcity.getSort() && !pcity.getSort().isEmpty() && null != pcity.getOrder() && !pcity.getOrder().isEmpty()){
			hql = hql+" order by "+ pcity.getSort() + " " + pcity.getOrder();
		}
		List<Pcity> pcities = cityDao.findwithState(hql, pcity.getPage(), pcity.getRows());
		d.setRows(pcities);
		d.setTotal(cityDao.Count(count));
		return d;
	}
	
	private void changeModelTd(List<City> citys, List<Pcity> pcitys) {
		for(int i=0;i<citys.size();i++){
			Hibernate.initialize(citys.get(i).getState());
			Pcity p = new Pcity();
			BeanUtils.copyProperties(citys.get(i), p);
			pcitys.add(p);
		}
			
	}
	@Override
	public Json addCity(Pcity pcity) {
		Json j = new Json();
		try {
			pcity.setId(WhesdtlUtil.getUUID());
			City city = new City();
			State state = stateDao.get("from State where id =:stateId", pcity);
			if(null != state){
				city.setState(state);
			}
			hitaii.util.BeanUtils.copyProperties(pcity, city);
			cityDao.save(city);
			j.setMsg("success");
			j.setSuccess(true);
		} catch (BeansException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@Override
	public Json editCity(Pcity pcity) {	
		Json j = new Json();
		City city = cityDao.get("from City where id =:id", pcity);
		if(null != city){
			State state = stateDao.get("from State where id =:stateId", pcity);
			if(null != state){
				city.setState(state);
			}
			hitaii.util.BeanUtils.copyProperties(pcity, city);
			cityDao.update(city);
			j.setMsg("success");
			j.setSuccess(true);
		}else{
			j.setMsg("model is not exist");
		}
		return j;
	}
	
	@Override
	public Json findCityById(Pcity pcity) {
		Json j = new Json();
		City city = cityDao.get("from City where id =:id", pcity);
		if(null != city){
			BeanUtils.copyProperties(city, pcity, new String[]{"state"});
			pcity.setStateId(city.getState().getId());
			j.setMsg("success");
			j.setSuccess(true);
			j.setObj(pcity);
		}else{
			j.setMsg("city is not exist");
		}
		return j;
	}
	
	@Override
	public boolean deleteCity(Pcity pcity) {
		if("".equals(pcity.getId()) || null == pcity.getId()){
			return false;
		}
		City city=new City();
		BeanUtils.copyProperties(pcity, city);
		cityDao.delete(city);
		return true;
	}
	
	@Override
	public List<City> findCityByStateId(Pcity city) {
		List<City> cities = cityDao.find("from City where stateId =:stateId order by city", city);
		return cities;
	}
	
}
