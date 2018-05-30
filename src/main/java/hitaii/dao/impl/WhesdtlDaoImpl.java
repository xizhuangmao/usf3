package hitaii.dao.impl;

import hitaii.dao.WhesdtlDaoI;
import hitaii.model.Booknum;
import hitaii.model.Make;
import hitaii.model.Whesdtl;
import hitaii.pageModel.Pbooknum;
import hitaii.pageModel.PcurrentStockReport;
import hitaii.pageModel.PdailyLoading;
import hitaii.pageModel.Pmemo;
import hitaii.pageModel.Pmodel;
import hitaii.pageModel.Porders;
import hitaii.pageModel.Pvessel;
import hitaii.pageModel.Pvoyage;
import hitaii.pageModel.Pwhesdtl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hitaii.dao.WhesdtlDaoI;
import hitaii.model.Carrier;
import hitaii.model.Make;
import hitaii.model.Memo;
import hitaii.model.Orders;
import hitaii.model.Pic;
import hitaii.model.Vessel;
import hitaii.model.Voyage;
import hitaii.model.Whesdtl;
import hitaii.model.Booknum;
import hitaii.pageModel.Porders;

@Repository("whesdtlDao")
public class WhesdtlDaoImpl extends BaseDaoImpl<Whesdtl> implements WhesdtlDaoI {
	
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

	public List<Pwhesdtl> findwithbooknum(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createSQLQuery(hql);
		
		q.setResultTransformer(Transformers.aliasToBean(Pwhesdtl.class));
		List<Pwhesdtl> pwhesdtlList = new ArrayList<Pwhesdtl>();
		if(page==0){
			pwhesdtlList = q.list();
		}else{
			pwhesdtlList = q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
		}
		return pwhesdtlList;
		
	}
	
	@Override
	public List<Pwhesdtl> findwithPic(String hql) {
		Query q = this.getCurrentSession().createSQLQuery(hql);
		
		//返回结果
		List<Pwhesdtl> whesdtlList = new ArrayList<Pwhesdtl> ();
		//查询结果
		List<Object[]> oList =new ArrayList<Object[]>();
		
		oList=q.list();
		
		for(Object[] o: oList){
			Pwhesdtl whesdtl = new Pwhesdtl();
			whesdtl.setModel(o[0].toString());
			whesdtl.setName(o[1].toString());
			whesdtl.setPath(o[2].toString());
			whesdtl.setMake(o[3].toString());
			whesdtl.setVin(o[4].toString());
			whesdtlList.add(whesdtl);
		}
		
		return whesdtlList;
	}
	
	public List<Pwhesdtl> findWareHousewithbooknum(String hql) {
		Query q = this.getCurrentSession().createSQLQuery(hql);
		
		q.setResultTransformer(Transformers.aliasToBean(Pwhesdtl.class));
		
		return q.list();
	}
	
	public String judge(Object data){
		if(null != data){
			return data.toString();
		}else{
			return "";
		}
	}
	
	@Override
	public List<Pwhesdtl> findWhesdtlNotUOO(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		//返回结果
		List<Pwhesdtl> whesdtlList = new ArrayList<Pwhesdtl> ();
		//查询结果
		List<Object[]> oList =new ArrayList<Object[]>();
		
		oList=q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
		for(Object[] o: oList){
			Pwhesdtl whesdtl = new Pwhesdtl();
			BeanUtils.copyProperties(o[1] , whesdtl);
			BeanUtils.copyProperties(o[0] , whesdtl);
			whesdtlList.add(whesdtl);
		}
		return whesdtlList;
	}
	
	@Override
	public List<Pmemo> findMemowithbooknum(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createSQLQuery(hql);
		
		q.setResultTransformer(Transformers.aliasToBean(Pmemo.class));
		List<Pmemo> pmemoList = new ArrayList<Pmemo>();
		if(page==0){
			pmemoList = q.list();
		}else{
			pmemoList = q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
		}
		return pmemoList;
	}
	
	@Override
	public List<Pwhesdtl> findwithBooknumVoyageOrders(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createSQLQuery(hql);
		
		q.setResultTransformer(Transformers.aliasToBean(Pwhesdtl.class));

		List<Pwhesdtl> pwhesdtls = new ArrayList<Pwhesdtl>();
		if(page==0){
			pwhesdtls = q.list();
		}else{
			pwhesdtls = q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
		}
		return pwhesdtls;
	}
	
	
	@Override
	public Long findwithCount(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		long count = (Long) q.uniqueResult();
		return count;
	}
	
	@Override
	public Long findCount(String hql) {
		Query q = this.getCurrentSession().createSQLQuery(hql);
		BigInteger count = (BigInteger) q.uniqueResult();
		Long cou = count.longValue();
		return cou;
	}
	
	@Override
	public void deleteWhesdtl(Whesdtl whesdtl) {
		this.getCurrentSession().delete(whesdtl);
	}
	
	@Override
	public void savewhesdtl(Whesdtl whesdtl) {
		this.getCurrentSession().save(whesdtl);
	}
	
	@Override
	public void saveOrUpdatewhesdtl(Whesdtl whesdtl) {
		this.getCurrentSession().saveOrUpdate(whesdtl);
	}

	@Override
	public void saveOrUpdateWhesdtl(Whesdtl whesdtl) {
		this.getCurrentSession().saveOrUpdate(whesdtl);
	}

	@Override
	public List<Porders> findwithOrders(String sql, int page, int rows) {

		Query q =this.getCurrentSession().createSQLQuery(sql);
		
		q.setResultTransformer(Transformers.aliasToBean(Porders.class));
		List<Porders> porders = new ArrayList<Porders>();
		if(page==0){
			porders = q.list();
		}else{
			porders = q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
		}
		return porders;
	}

	@Override
	public Long findMemowithCount(String hqlCount) {
		Query q = this.getCurrentSession().createSQLQuery(hqlCount);
		BigInteger count = (BigInteger) q.uniqueResult();
		Long cou = count.longValue();
		return cou;
	}
	
	public List<Pvessel> findVesselToCarrier(String sql, int page, int rows){
		Query q = this.getCurrentSession().createQuery(sql);
		
		q.setResultTransformer(Transformers.aliasToBean(Pvessel.class));
		List<Pvessel> pvesselList = new ArrayList<Pvessel>();
		if(page==0){
			pvesselList = q.list();
		}else{
			pvesselList = q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
		}
		return pvesselList;
			
	}
	
	@Override
	public List<PdailyLoading> getPdailyLoadingDate(String sql) {
		Query q = this.getCurrentSession().createQuery(sql);
		
		q.setResultTransformer(Transformers.aliasToBean(PdailyLoading.class));
		
		return q.list();
	}


	@Override
	public void updateSomeWhesdtlBooknumId(String sql) {
		this.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public Whesdtl getPic(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<Whesdtl> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public Whesdtl getWhesdtl(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<Whesdtl> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}
	
	@Override
	public void updatePic(Whesdtl whesdtl) {
		this.getCurrentSession().update(whesdtl);
	}


	@Override
	public List<Pwhesdtl> findWareHousewithMake(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		
		//返回结果
		List<Pwhesdtl> whesdtlList = new ArrayList<Pwhesdtl> ();
		//查询结果
		List<Object[]> oList =new ArrayList<Object[]>();
		
		oList=q.list();
		
		for(Object[] o: oList){
			Pwhesdtl whesdtl = new Pwhesdtl();
			BeanUtils.copyProperties(o[0], whesdtl);
			whesdtl.setMakeId(((Make)o[1]).getId());
			whesdtlList.add(whesdtl);
		}
		
		return whesdtlList;
	}

	@Override
	public void updateWarehouseInfo(Whesdtl whesdtl) {
		this.getCurrentSession().update(whesdtl);
	}

	@Override
	public List<Whesdtl> findVehicleInfo(String hql) {
		Query q =this.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@Override
	public List<Pmodel> findMakeAndModel(String sql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(sql);
		
		q.setResultTransformer(Transformers.aliasToBean(Pmodel.class));
		List<Pmodel> pmodels = new ArrayList<Pmodel>();
		if(page==0){
			pmodels = q.list();
		}else{
			pmodels = q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
		}
		return pmodels;
	}

	@Override
	public List<PcurrentStockReport> getCurrentStockReport(String sql) {
		Query q = this.getCurrentSession().createQuery(sql);
		q.setResultTransformer(Transformers.aliasToBean(PcurrentStockReport.class));
		return q.list();
	}

	@Override
	public Booknum findEditBookingNum(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<Booknum> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public List<Whesdtl> findNotOrders(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);	
		return q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}


	@Override
	public List<Pbooknum> findWithWhesdtl(String hql, int page, int rows) {
		Query q =this.getCurrentSession().createSQLQuery(hql);
		
		q.setResultTransformer(Transformers.aliasToBean(Pbooknum.class));
		List<Pbooknum> pbooknums = new ArrayList<Pbooknum>();
		if(page==0){
			pbooknums = q.list();
		}else{
			pbooknums = q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
		}
		return pbooknums;
	}
	
	@Override
	public List<Whesdtl> findW(String whesdtlHql, Pbooknum pbooknum) {
		Query q = this.getCurrentSession().createQuery(whesdtlHql);
		q.setProperties(pbooknum);
		return q.list();
	}
	
	@Override
	public List<Whesdtl> findPreAlert(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);	
		return q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}

	@Override
	public List<Whesdtl> findByOrdersId(String hql, Object o) {
		Query q = this.getCurrentSession().createQuery(hql);
		q.setProperties(o);
		return q.list();
	}
	
	@Override
	public Whesdtl getVehicleInfo(String hql, Object o) {
		Query q = this.getCurrentSession().createQuery(hql);
		q.setProperties(o);
		List<Whesdtl> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public List<Pwhesdtl> findWithOrders(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		//返回结果
		List<Pwhesdtl> whesdtlList = new ArrayList<Pwhesdtl> ();
		//查询结果
		List<Object[]> oList =new ArrayList<Object[]>();
		
		oList=q.list();
		for(Object[] o: oList){
			Pwhesdtl whesdtl = new Pwhesdtl();
			BeanUtils.copyProperties(o[2] , whesdtl);
			BeanUtils.copyProperties(o[1] , whesdtl);
			BeanUtils.copyProperties(o[0] , whesdtl);
			whesdtlList.add(whesdtl);
		}
		return whesdtlList;
	}

	@Override
	public List<Pwhesdtl> findUooAndWhesdtlServices(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		//返回结果
		List<Pwhesdtl> whesdtlList = new ArrayList<Pwhesdtl> ();
		//查询结果
		List<Object[]> oList =new ArrayList<Object[]>();
		
		oList=q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
		for(Object[] o: oList){
			Pwhesdtl whesdtl = new Pwhesdtl();
			BeanUtils.copyProperties(o[1] , whesdtl);
			BeanUtils.copyProperties(o[0] , whesdtl);
			whesdtlList.add(whesdtl);
		}
		return whesdtlList;
	}

}
