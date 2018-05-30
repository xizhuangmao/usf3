package hitaii.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hitaii.dao.CompanyDaoI;
import hitaii.dao.TruckDaoI;
import hitaii.model.Company;
import hitaii.model.Role;
import hitaii.model.Truck;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Pcompany;
import hitaii.pageModel.Prole;
import hitaii.pageModel.Ptruck;
import hitaii.service.TruckServiceI;
import hitaii.util.WhesdtlUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("truckService")
public class TruckServiceImpl implements TruckServiceI{
	private TruckDaoI truckDao;
	private CompanyDaoI companyDao;
	
	public CompanyDaoI getCompanyDao() {
		return companyDao;
	}
	@Autowired
	public void setCompanyDao(CompanyDaoI companyDao) {
		this.companyDao = companyDao;
	}
	public TruckDaoI getTruckDao() {
		return truckDao;
	}
	@Autowired
	public void setTruckDao(TruckDaoI truckDao) {
		this.truckDao = truckDao;
	}

	@Override
	public List<Pcompany> findTruckCoName() {
		List<Pcompany> pcompanies = new ArrayList<Pcompany> ();
		String hql = "from Company where types = '3' order by fullname";
		List<Company> companys = companyDao.find(hql);
		for(Company c : companys){
			Pcompany pcompany = new Pcompany();
			hitaii.util.BeanUtils.copyProperties(c, pcompany);
			pcompanies.add(pcompany);
		}
		return pcompanies;
	}
	
	@Override
	public DataGrid getAllTruckDataGrid(Ptruck ptruck){
		DataGrid d = new DataGrid();
		List<Truck> truckList = new ArrayList<Truck>(); 
		List<Ptruck> ptruckList = new ArrayList<Ptruck>(); 
		String hql ="from Truck t ";
		hql = addWhere(ptruck, hql);
		hql = addOrder(ptruck, hql);
		truckList = truckDao.find(hql,ptruck,ptruck.getPage(), ptruck.getRows());
		
		changeModel(truckList, ptruckList);
		
		d.setRows(ptruckList);
		d.setTotal(truckDao.count("select count(*)"+hql,ptruck));
		return d;
		
	}
	
	private void changeModel(List<Truck> truckList, List<Ptruck> ptruckList) {
		if(null!=truckList && truckList.size()>0){
			for(Truck truck: truckList){
				Ptruck u = new Ptruck();
				BeanUtils.copyProperties(truck, u);
				ptruckList.add(u);
			}
			
		}
	}

	private String addOrder(Ptruck ptruck, String hql) {
		if(null!=ptruck.getSort()){
			hql = hql+" order by "+ptruck.getSort();
			if(null!=ptruck.getOrder()){
				hql=hql+" "+ptruck.getOrder();
			}
		}
		return hql;
	}

	private String addWhere(Ptruck ptruck, String hql) {
		hql +=" where 1=1 ";
		if(null!=ptruck.getFullname()&&!ptruck.getFullname().trim().equals("")){
			hql +=" and fullname like :fullname ";
			ptruck.setFullname("%%"+ptruck.getFullname()+"%%");
		}
		if(null!=ptruck.getCountry()&&!ptruck.getCountry().trim().equals("")){
			hql +=" and country = :country ";
		}
		if(null!=ptruck.getState()&&!ptruck.getState().trim().equals("")){
			hql +=" and state = :state ";
		}
		if(null!=ptruck.getCity()&&!ptruck.getCity().trim().equals("")){
			hql +=" and city = :city ";
		}
		return hql;
	}
	
	@Override
	public boolean saveOneTruck(Ptruck ptruck) {
		if(null==ptruck){
			return false;
		}
		if(null==ptruck.getFullname() || "".equals(ptruck.getFullname().trim())){
			return false;
		}
		if(null==ptruck.getActive() || "".equals(ptruck.getActive().trim())){
			ptruck.setActive("1");
		}
		Truck truck = new Truck();
		BeanUtils.copyProperties(ptruck, truck);
		try {
			if(null==ptruck.getId() || "".equals(ptruck.getId().trim())){
				truck.setId(WhesdtlUtil.getUUID());
				truckDao.save(truck);
			}else{
				truckDao.update(truck);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	public Ptruck findOneTruck(Ptruck ptruck) {
		if(null==ptruck.getId() || "".equals(ptruck.getId().trim())){
			return null;
		}
		String hql = "from Truck where id = :id";
		Truck truck = truckDao.get(hql, ptruck);
		if(null == truck){
			return null;
		}
		BeanUtils.copyProperties(truck, ptruck);
		return ptruck;
	}
	
	
	
	
	
	
	
	
	
}
