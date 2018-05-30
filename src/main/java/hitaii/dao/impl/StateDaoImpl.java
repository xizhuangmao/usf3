package hitaii.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import hitaii.dao.StateDaoI;
import hitaii.model.Model;
import hitaii.model.State;
import hitaii.pageModel.Pmodel;
import hitaii.pageModel.Pstate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("stateDao")
public class StateDaoImpl extends BaseDaoImpl<State> implements StateDaoI {

	private SessionFactory sessionFactory;
	
	private Session getCurrentSession (){
		return this.sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Serializable save(State m) {
		return this.getCurrentSession().save(m);
	}

	@Override
	public List<State> find(String hql) {
		Query q =this.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@Override
	public List<State> find(String hql, Object o) {
		Query q = this.getCurrentSession().createQuery(hql);
		q.setProperties(o);
		return q.list();
	}
	
	@Override
	public Long Count(String hql) {
		Query q = this.getCurrentSession().createSQLQuery(hql);
		BigInteger count = (BigInteger) q.uniqueResult();
		Long cou = count.longValue();
		return cou;
	}
	
	@Override
	public State get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<State> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public State get(String hql, Object[] params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		List<State> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public State get(String hql, Object m) {
		Query q = this.getCurrentSession().createQuery(hql);
		q.setProperties(m);
		List<State> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public void delete(State s) {
		this.getCurrentSession().delete(s);
	}

	@Override
	public void update(State s) {
		this.getCurrentSession().update(s);
	}

	@Override
	public void saveOrUpdate(State s) {
		this.getCurrentSession().saveOrUpdate(s);
	}
	
	public List<Pstate> findwithCountry(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createSQLQuery(hql);
		
		q.setResultTransformer(Transformers.aliasToBean(Pstate.class));
		List<Pstate> pstates = new ArrayList<Pstate>();
		if(page==0){
			pstates = q.list();
		}else{
			pstates = q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
		}
		return pstates;
	}

}
