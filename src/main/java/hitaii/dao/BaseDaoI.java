package hitaii.dao;

import hitaii.model.Whesdtl;

import java.io.Serializable;
import java.util.List;

public interface BaseDaoI<T> {

	public Serializable save(T o);

	public void delete(T o);

	public void update(T o);

	public void saveOrUpdate(T o);
	
	public T get(Class<T> c,Serializable id);

	public T get(String hql);

	public T get(String hql, Object[] params);

	public T get(String hql, Object o);

	public List<T> find(String hql);

	public List<T> find(String hql, Object o);

	public List<T> find(String hql, int page, int rows);

	public List<T> find(String hql, Object o, int page, int rows);
	
	public Long count(String hql);
	
	public Long count(String hql, Object o);
	
	public int executeHql(String hql);
	
}
