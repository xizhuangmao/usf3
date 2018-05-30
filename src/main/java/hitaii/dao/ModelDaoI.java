package hitaii.dao;

import java.io.Serializable;
import java.util.List;

import hitaii.model.Model;
import hitaii.pageModel.Pmodel;

public interface ModelDaoI extends BaseDaoI<Model>{
	
	public List<Model> find(String hql);
	
	public List<Pmodel> findwithMake(String hql, int page, int rows);

	public Long Count(String hql);
	
	public Serializable save(Model m);
	
	public Model get(String hql);
	
	public Model get(String hql, Object[] params);
	
	public Model get(String hql, Object m);
	
	public void delete(Model m);
	
	public void update(Model m);
	
	public void saveOrUpdate(Model m);

}
