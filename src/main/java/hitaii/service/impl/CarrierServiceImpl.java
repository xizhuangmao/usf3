package hitaii.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hitaii.dao.CarrierDaoI;
import hitaii.dao.CompanyDaoI;
import hitaii.model.Carrier;
import hitaii.model.Company;
import hitaii.pageModel.Pcarrier;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Pcompany;
import hitaii.service.CarrierServiceI;
import hitaii.util.WhesdtlUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("carrierService")
public class CarrierServiceImpl implements CarrierServiceI{
	
	private CarrierDaoI carrierDao;
	private CompanyDaoI companyDao;
	
	public CompanyDaoI getCompanyDao() {
		return companyDao;
	}
	@Autowired
	public void setCompanyDao(CompanyDaoI companyDao) {
		this.companyDao = companyDao;
	}
	public CarrierDaoI getCarrierDao() {
		return carrierDao;
	}
	@Autowired
	public void setCarrierDao(CarrierDaoI carrierDao) {
		this.carrierDao = carrierDao;
	}
	
	@Override
	public List<Pcompany> findCarrierName() {
		String hql = "from Company where types = '4' order by fullname";
		List<Company> companies = companyDao.find(hql);
		List<Pcompany> pcompanies =new ArrayList<Pcompany>();
		if(null != companies && companies.size() > 0){
			for(Company c : companies){
				Pcompany pcompany = new Pcompany();
				BeanUtils.copyProperties(c, pcompany);
				pcompanies.add(pcompany);
			}
		}
		return pcompanies;
	}
	
	@Override
	public DataGrid getAllCarrierUsers(Pcarrier carrier) {
		DataGrid d = new DataGrid();
		List<Pcarrier> pcarriers = new ArrayList<Pcarrier>();
		String hql = "from Carrier";
		if(null != carrier.getSort() && !carrier.getSort().isEmpty() && null != carrier.getOrder() && !carrier.getOrder().isEmpty()){
			hql=hql+" order by "+ carrier.getSort() + " " + carrier.getOrder();
		}
		
		List<Carrier> carriers=carrierDao.find(hql,  carrier.getPage(), carrier.getRows());
		changeModelTd(carriers,pcarriers);
		d.setRows(carriers);
		
		d.setTotal(carrierDao.count("select count(*)"+hql,carrier));
		return d;
		
	}
	
	@Override
	public boolean saveOneCarrier(Pcarrier pcarrier) {
		if(null==pcarrier.getFullname()||"".equals(pcarrier.getFullname())){
			return false;
		}
		if(null==pcarrier.getFullname()||"".equals(pcarrier.getFullname())){
			return false;
		}
		if(null==pcarrier.getActive() || "".equals(pcarrier.getActive())){
			pcarrier.setActive("1");
		}
		pcarrier.setId(WhesdtlUtil.getUUID());
		pcarrier.setDatein(WhesdtlUtil.getNowTime());
		Carrier carrier = new Carrier();
		BeanUtils.copyProperties(pcarrier, carrier);
		carrierDao.save(carrier);
		return true;
	}
	
	private void changeModelTd(List<Carrier> carriers, List<Pcarrier> pcarriers) {
		if (null != carriers && carriers.size() > 0) {
			for (Carrier carrier : carriers) {
				Pcarrier pcarrier = new Pcarrier();
				BeanUtils.copyProperties(carrier, pcarrier);
				pcarriers.add(pcarrier);
			}
		}
	}
	@Override
	public Pcarrier findCarrierEdit(Pcarrier carrier) {
		if(null == carrier){
			return null;
		}
		String hql = "from Carrier where 1=1 ";
		if(null != carrier.getId() && !carrier.getId().isEmpty()){
			hql = hql+" and id = :id ";
		}
		if(null != carrier.getSort() && !carrier.getSort().isEmpty() && null != carrier.getOrder() && !carrier.getOrder().isEmpty()){
			hql=hql+" order by "+ carrier.getSort() + " " + carrier.getOrder();
		}
		Carrier c = carrierDao.get(hql, carrier);
		if(null == c){
			return null;
		}
		BeanUtils.copyProperties(c, carrier);
		return carrier;
	}
	
	public boolean updateOneCarrier(Pcarrier carrier){
		if(null==carrier.getFullname()||"".equals(carrier.getFullname())){
			return false;
		}
		if(null==carrier.getFullname()||"".equals(carrier.getFullname())){
			return false;
		}
		Carrier c = new Carrier();
		BeanUtils.copyProperties(carrier, c);
		carrierDao.update(c);
		return true;
	}
	
	@Override
	public boolean delectOneCarrier(Pcarrier pcarrier) {
		if(null == pcarrier){
			return false;
		}
		if(null==pcarrier.getId()||"".equals(pcarrier.getId())){
			return false;
		}
		Carrier carrier=new Carrier();
		BeanUtils.copyProperties(pcarrier, carrier);
		carrierDao.delete(carrier);
		return true;
	}	
	
	@Override
	public List<Map<String, String>> findAllCarrier() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>> ();
		String hql = "from Carrier";
		List<Carrier> carriers = carrierDao.find(hql);
		for(int i =0;i<carriers.size();i++){
			Map<String, String> map = new HashMap<String,String>();
			map.put("fullName",  carriers.get(i).getFullname());
			map.put("id", carriers.get(i).getId());
			list.add(map);
		}
		return list;
	}
}
