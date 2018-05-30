package hitaii.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.BooknumDaoI;
import hitaii.dao.VoyageDaoI;
import hitaii.dao.WhesdtlDaoI;
import hitaii.model.Booknum;
import hitaii.model.Model;
import hitaii.model.Vessel;
import hitaii.model.Voyage;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pvessel;
import hitaii.pageModel.Pvoyage;
import hitaii.service.VoyageServiceI;
import hitaii.util.WhesdtlUtil;


@Service("voyageService")
public class VoyageServiceImpl implements VoyageServiceI {
	
	private VoyageDaoI voyageDao;
	private BooknumDaoI booknumDao;
	
	public BooknumDaoI getBooknumDao() {
		return booknumDao;
	}
	@Autowired
	public void setBooknumDao(BooknumDaoI booknumDao) {
		this.booknumDao = booknumDao;
	}
	public VoyageDaoI getVoyageDao() {
		return voyageDao;
	}
	@Autowired
	public void setVoyageDao(VoyageDaoI voyageDao) {
		this.voyageDao = voyageDao;
	}
	private WhesdtlDaoI whesdtlDao;
	public WhesdtlDaoI getWhesdtlDao() {
		return whesdtlDao;
	}
	@Autowired
	public void setWhesdtlDao(WhesdtlDaoI whesdtlDao) {
		this.whesdtlDao = whesdtlDao;
	}

	@Override
	public List<Pvoyage> findVoyageName(Pvoyage pvoyage) throws BeansException, ParseException {
		List<Pvoyage> pvoyages = new ArrayList<Pvoyage> ();
		List<Voyage> voyages =new ArrayList<Voyage> ();
		String hql = "";
		if(null==pvoyage.getVesselId() || "".equals(pvoyage.getVesselId())){
			hql = "from Voyage order by voyage";
			voyages =voyageDao.find(hql);
		}else{
			hql = "from Voyage where vesselId = :vesselId order by voyage";
			voyages =voyageDao.find(hql,pvoyage);
		}
		Date date = new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String time=format.format(date); 
		
		if (null != voyages && voyages.size() > 0) {
			for (Voyage voyage : voyages) {
				if(null == voyage.getCutoffdate() || voyage.getCutoffdate().isEmpty() || format.parse(voyage.getCutoffdate()).after(format.parse(time)) || 
						format.parse(voyage.getCutoffdate()).equals(format.parse(time))){
					Pvoyage ppvoyage = new Pvoyage();
					BeanUtils.copyProperties(voyage, ppvoyage);
					pvoyages.add(ppvoyage);
				}
			}
		}
		return pvoyages;
	}
	
	private void changeModelTd(List<Voyage> voyages,List<Pvoyage> pvoyages) {
		if (null != voyages && voyages.size() > 0) {
			for (Voyage voyage : voyages) {
				Pvoyage pvoyage = new Pvoyage();
				BeanUtils.copyProperties(voyage, pvoyage);
				pvoyages.add(pvoyage);
			}
		}
	}
	
	@Override
	public DataGrid getVoyageDataGrid(Pvoyage voyage) {
		DataGrid d = new DataGrid();
		String hql = " from Voyage vo left join Vessel vv on vo.vesselId = vv.id left join Company c on vv.carrierId = c.id where 1=1 ";

		if(null != voyage.getCarrierId() && !voyage.getCarrierId().isEmpty()){
			hql = hql + " and c.id='"+voyage.getCarrierId()+"'";
		}
		if(null != voyage.getVesselId() && !voyage.getVesselId().isEmpty()){
			hql = hql + " and vv.id='"+voyage.getVesselId()+"'";
		}
		if(null != voyage.getVoyage() && !voyage.getVoyage().isEmpty()){
			hql = hql + " and vo.voyage='"+voyage.getVoyage()+"'";
		}
		String sql = "select vo.id as id,vo.voyage as voyage,vv.carrierId as carrierId,vv.id as vesselId,vv.vessel as vessel,vo.active as active,vo.note as note,c.fullname as fullname,vo.eta as eta,vo.etd as etd,vo.cutoffdate as cutoffdate,vo.terminal as terminal "+hql;
		if(null != voyage.getSort() && !voyage.getSort().isEmpty() && null != voyage.getOrder() && !voyage.getOrder().isEmpty()){
			sql=sql+" order by "+ voyage.getSort() + " " + voyage.getOrder();
		}
		List<Pvoyage> pvoyages = voyageDao.findVesselAndCarrierAndVoyage(sql, voyage.getPage(), voyage.getRows());
		for(Pvoyage p : pvoyages){
			List<Booknum> booknums = booknumDao.find("from Booknum where voyageId =:id", p);
			if(null != booknums && booknums.size() > 0){
				p.setType("1");
			}
		}
		d.setRows(pvoyages);
		
		d.setTotal(whesdtlDao.findMemowithCount("select count(*)"+hql));
		return d;
	}
	
	@Override
	public Json addVoyage(Pvoyage pvoyage) {
		Json j = new Json();
		try {
			pvoyage.setId(WhesdtlUtil.getUUID());
			pvoyage.setActive("1");
			Voyage voyage = new Voyage();
			BeanUtils.copyProperties(pvoyage, voyage);
			voyageDao.save(voyage);
			j.setMsg("success");
			j.setSuccess(true);
		} catch (BeansException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@Override
	public Pvoyage findVoyageById(Pvoyage voyage) {
		if("".equals(voyage.getId())|| null == voyage.getId()){
			return null;
		}
		String hql = " from Voyage vo left join Vessel vv on vo.vesselId = vv.id left join Company c on vv.carrierId = c.id where 1=1 ";
		hql = hql + " and vo.id = '"+voyage.getId()+"'";
		String sql = "select vo.id as id,vo.voyage as voyage,vv.carrierId as carrierId,vv.vessel as vessel,vv.id as vesselId,vo.active as active,vo.note as note,c.fullname as carrier,vo.eta as eta,vo.etd as etd,vo.cutoffdate as cutoffdate,vo.terminal as terminal "+hql;
		List<Pvoyage> pvoyages = voyageDao.findVesselAndCarrierAndVoyage(sql,0, 0);
		if(pvoyages.size()>0){
			voyage = pvoyages.get(0);
		}
		return voyage;
	}
	@Override
	public boolean deleteVoyage(Pvoyage pvoyage) {
		if("".equals(pvoyage.getId()) || null == pvoyage.getId()){
			return false;
		}
		Voyage voyage=new Voyage();
		BeanUtils.copyProperties(pvoyage, voyage);
		voyageDao.delete(voyage);
		return true;
	}
	
	@Override
	public Pvoyage getVoyageById(Pvoyage pvoyage) {
		if(null==pvoyage.getId() || "".equals(pvoyage.getId())){
			return null;
		}
		String hql = "from Voyage where id = :id";
		Voyage voyage = voyageDao.get(hql, pvoyage);
		if(null !=voyage){
			BeanUtils.copyProperties(voyage, pvoyage);
			return pvoyage;
		}
		return null;
	}
	
	@Override
	public Json editVoyage(Pvoyage pvoyage) {
		Json j = new Json();
		Voyage voyage = voyageDao.get("from Voyage where id =:id", pvoyage);
		if(null != voyage){
			hitaii.util.BeanUtils.copyProperties(pvoyage, voyage);
			voyageDao.update(voyage);
			j.setMsg("success");
			j.setSuccess(true);
		}else{
			j.setMsg("voyage is not exist");
		}
		return j;
	}
}
