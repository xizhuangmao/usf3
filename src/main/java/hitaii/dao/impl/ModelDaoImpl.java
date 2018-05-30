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

import hitaii.dao.ModelDaoI;
import hitaii.model.Model;
import hitaii.pageModel.Pmodel;

@Repository("modelDao")
public class ModelDaoImpl extends BaseDaoImpl<Model> implements ModelDaoI {
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
	public Serializable save(Model m) {
		return this.getCurrentSession().save(m);
	}

	@Override
	public List<Model> find(String hql) {
		Query q =this.getCurrentSession().createQuery(hql);
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
	public Model get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<Model> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public Model get(String hql, Object[] params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		List<Model> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public Model get(String hql, Object m) {
		Query q = this.getCurrentSession().createQuery(hql);
		q.setProperties(m);
		List<Model> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public void delete(Model m) {
		this.getCurrentSession().delete(m);
	}

	@Override
	public void update(Model m) {
		this.getCurrentSession().update(m);
	}

	@Override
	public void saveOrUpdate(Model m) {
		this.getCurrentSession().saveOrUpdate(m);
	}
	
	public List<Pmodel> findwithMake(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createSQLQuery(hql);
		
		q.setResultTransformer(Transformers.aliasToBean(Pmodel.class));
		List<Pmodel> pmodels = new ArrayList<Pmodel>();
		if(page==0){
			pmodels = q.list();
		}else{
			pmodels = q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
		}
		return pmodels;
	}
}
