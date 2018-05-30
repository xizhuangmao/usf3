package hitaii.service.impl;

import java.util.List;

import hitaii.dao.CarrierDaoI;
import hitaii.dao.VesselDaoI;
import hitaii.dao.WhesdtlDaoI;
import hitaii.model.Vessel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pvessel;

import hitaii.service.VesselServiceI;
import hitaii.util.WhesdtlUtil;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;


import org.springframework.stereotype.Service;

@Service("vesselService")
public class VesselServiceImpl implements VesselServiceI{
	
	private VesselDaoI vesselDao;
	private WhesdtlDaoI whesdtlDao;
	private CarrierDaoI carrierDao;
	
	public CarrierDaoI getCarrierDao() {
		return carrierDao;
	}
	@Autowired
	public void setCarrierDao(CarrierDaoI carrierDao) {
		this.carrierDao = carrierDao;
	}
	public VesselDaoI getVesselDao() {
		return vesselDao;
	}
	@Autowired
	public void setVesselDao(VesselDaoI vesselDao) {
		this.vesselDao = vesselDao;
	}
	public WhesdtlDaoI getWhesdtlDao() {
		return whesdtlDao;
	}
	@Autowired
	public void setWhesdtlDao(WhesdtlDaoI whesdtlDao) {
		this.whesdtlDao = whesdtlDao;
	}
	
	
	
	@Override
	public List<Vessel> findVesselByCarrierId(String carrierId) {
		String hql = "from Vessel where carrierId = '"+carrierId+"'";
		return vesselDao.find(hql);
	}

	@Override
	public List<Map<String, String>> findVesselName(Pvessel pvessel) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>> ();
		String hql = "";
		List<Vessel> vessels = null;
		if(null==pvessel.getCarrierId() || "".equals(pvessel.getCarrierId())){
			hql = "from Vessel order by vessel";
			vessels =vesselDao.find(hql);
		}else{
			 hql = "from Vessel where carrierId= :carrierId order by vessel";
			 vessels =vesselDao.find(hql,pvessel);
		}
		if(vessels.size()>0){
			for(int i=0;i<vessels.size();i++){
				Vessel vessel =vessels.get(i);
				Map<String, String> map = new HashMap<String,String>();
				map.put("vessel",  vessel.getVessel());
				map.put("id", vessel.getId());
				list.add(map);
			}
		}
		return list;
	}
	/**
	 * 
	 
	@Override
	public List<Map<String, String>> findVesselName(Pvessel vessel) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>> ();
		String hql = "from Vessel where carrierId= :carrierId";
		List<Vessel> vessels =vesselDao.find(hql,vessel);
		for(int i=0;i<vessels.size();i++){
			Vessel v =vessels.get(i);
			Map<String, String> map = new HashMap<String,String>();
			map.put("vessel",  v.getVessel());
			map.put("id", v.getId());
			list.add(map);
		}
		return list;
	}
	*/
	
	
	@Override
	public List<Pvessel> findByCarrierId(Pvessel vessel) {
		List<Pvessel> pageVessels = new ArrayList<Pvessel>();
		String hql = "from Vessel where carrierId = :id";
		List<Vessel> vessels=vesselDao.find(hql, vessel);
		if(vessels.size()>0){
			changeModelTd(vessels,pageVessels);
		}
		return pageVessels;
	}

	private void changeModelTd(List<Vessel> vessels,
			List<Pvessel> pageVessels) {
		
		if (null != vessels && vessels.size() > 0) {
			for (Vessel v : vessels) {
				Pvessel pVessel = new Pvessel();
				BeanUtils.copyProperties(v, pVessel);
				pageVessels.add(pVessel);
			}
		}
	}
	@Override
	public DataGrid getVesselDataGrid(Pvessel pvessel) {
		DataGrid d = new DataGrid();
		String hql = " from Vessel v ,Company c where v.carrierId = c.id";
		//条件判断
		if("".equals(pvessel.getCarrierId()) || null == pvessel.getCarrierId()){
		}else{
			hql = hql + " and c.id = '"+pvessel.getCarrierId()+"'";
		}
		if("".equals(pvessel.getVessel()) || null == pvessel.getVessel()){
		}else{
			hql = hql + " and v.vessel like '%"+pvessel.getVessel()+"%'";
		}
		String sql = "select v.id as id,v.carrierId as carrierId,v.vessel as vessel,v.active as active,v.note as note,c.fullname as fullname "+hql;
		if(null != pvessel.getSort() && !pvessel.getSort().isEmpty() && null != pvessel.getOrder() && !pvessel.getOrder().isEmpty()){
			sql=sql+" order by "+ pvessel.getSort() + " " + pvessel.getOrder();
		}
		List<Pvessel> pvessels=whesdtlDao.findVesselToCarrier(sql, pvessel.getPage(), pvessel.getRows());
		d.setRows(pvessels);
		
		d.setTotal(whesdtlDao.findMemowithCount("select count(*)"+hql));
		return d;
	}
	@Override
	public Json findVesselById(Pvessel pvessel) {
		Json j = new Json();
		Vessel vessel = vesselDao.get("from Vessel where id = :id", pvessel);
		if(null != vessel){
			BeanUtils.copyProperties(vessel, pvessel);
			j.setObj(pvessel);
			j.setSuccess(true);
		}else{
			j.setMsg("vessel is not exist");
		}
		return j;
	}
	
	@Override
	public Json addVessel(Pvessel pvessel) {
		Json j = new Json();
		try {
			pvessel.setId(WhesdtlUtil.getUUID());
			pvessel.setActive("1");
			Vessel vessel = new Vessel();
			BeanUtils.copyProperties(pvessel, vessel);
			vesselDao.save(vessel);
			j.setMsg("success");
			j.setSuccess(true);
		} catch (BeansException e) {
			j.setMsg(e.getMessage());
		}
		return j;

	}
	
	@Override
	public boolean deleteVessel(Pvessel pvessel) {
		if("".equals(pvessel.getId()) || null == pvessel.getId()){
			return false;
		}
		Vessel vessel=new Vessel();
		BeanUtils.copyProperties(pvessel, vessel);
		vesselDao.delete(vessel);
		return true;
	}

	@Override
	public Json editVessel(Pvessel pvessel) {
		Json j = new Json();
		Vessel vessel = vesselDao.get("from Vessel where id =:id", pvessel);
		if(null != vessel){
			hitaii.util.BeanUtils.copyProperties(pvessel, vessel);
			vesselDao.update(vessel);
			j.setMsg("success");
			j.setSuccess(true);
		}else{
			j.setMsg("vessel is not exist");
		}
		return j;
	}

}
