package hitaii.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hitaii.dao.CityDaoI;
import hitaii.model.City;
import hitaii.pageModel.Pcity;

@Repository("cityDao")
public class CityDaoImpl extends BaseDaoImpl<City> implements CityDaoI {

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
	public Serializable save(City c) {
		return this.getCurrentSession().save(c);
	}

	@Override
	public List<City> find(String hql) {
		Query q =this.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
	@Override
	public List<City> find(String hql, Object o) {
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
	public City get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<City> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public City get(String hql, Object[] params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		List<City> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public City get(String hql, Object m) {
		Query q = this.getCurrentSession().createQuery(hql);
		q.setProperties(m);
		List<City> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public void delete(City c) {
		this.getCurrentSession().delete(c);
	}

	@Override
	public void update(City c) {
		this.getCurrentSession().update(c);
	}

	@Override
	public void saveOrUpdate(City c) {
		this.getCurrentSession().saveOrUpdate(c);
	}
	
	public List<Pcity> findwithState(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createSQLQuery(hql);
		
		q.setResultTransformer(Transformers.aliasToBean(Pcity.class));
		List<Pcity> pcities = new ArrayList<Pcity>();
		if(page==0){
			pcities = q.list();
		}else{
			pcities = q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
		}
		return pcities;
	}

}
