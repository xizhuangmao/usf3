package hitaii.dao;

import java.io.Serializable;
import java.util.List;

import hitaii.model.State;
import hitaii.pageModel.Pstate;

public interface StateDaoI extends BaseDaoI<State>{

	public List<State> find(String hql);
	
	public List<State> find(String hql, Object o);
	
	public Long Count(String hql);
	
	public Serializable save(State s);
	
	public State get(String hql);
	
	public State get(String hql, Object[] params);
	
	public State get(String hql, Object s);
	
	public void delete(State s);
	
	public void update(State s);
	
	public void saveOrUpdate(State s);
	
	public List<Pstate> findwithCountry(String hql, int page, int rows);

}
