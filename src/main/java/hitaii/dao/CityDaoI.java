package hitaii.dao;

import java.io.Serializable;
import java.util.List;

import hitaii.model.City;
import hitaii.pageModel.Pcity;

public interface CityDaoI extends BaseDaoI<City>{
	
	public List<City> find(String hql);
	
	public List<City> find(String hql, Object o);
	
	public Long Count(String hql);
	
	public Serializable save(City c);
	
	public City get(String hql);
	
	public City get(String hql, Object[] params);
	
	public City get(String hql, Object c);
	
	public void delete(City c);
	
	public void update(City c);
	
	public void saveOrUpdate(City c);
	
	public List<Pcity> findwithState(String hql, int page, int rows);

}
