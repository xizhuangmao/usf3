package hitaii.dao;


import java.io.Serializable;
import java.util.List;

import hitaii.model.Model;
import hitaii.model.Voyage;
import hitaii.pageModel.Pmodel;
import hitaii.pageModel.Pvoyage;


public interface VoyageDaoI extends BaseDaoI<Voyage>{
	
	public List<Voyage> find(String hql);

	public Long Count(String hql);
	
	public Serializable save(Voyage m);
	
	public Voyage get(String hql);
	
	public Voyage get(String hql, Object[] params);
	
	public Voyage get(String hql, Object m);
	
	public void delete(Voyage m);
	
	public void update(Voyage m);
	
	public void saveOrUpdate(Voyage m);
	
	public List<Voyage> find(String hql, Object o);
	
	public List<Pvoyage> findVesselAndCarrierAndVoyage(String sql, int page, int rows);

}
