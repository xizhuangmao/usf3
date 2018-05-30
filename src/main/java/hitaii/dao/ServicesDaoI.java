package hitaii.dao;

import java.io.Serializable;
import java.util.List;

import hitaii.model.Services;
import hitaii.pageModel.Pservices;

public interface ServicesDaoI extends BaseDaoI<Services>{
	
	public List<Pservices> findwithWhesNvocc(String hql, int page, int rows);
	
	public Long findCount(String hql);
	
	public List<Services> findServices(String hql);
	
	public Serializable saveServices(Services o);
	
	public Services getService(String hql);
	
	public void deleteService(Services o);
	
	public void updateService(Services o);
	
	public List<Services> findServices(String hql, Object o);

	public List<Services> findServices(String hql, int page, int rows);
}
