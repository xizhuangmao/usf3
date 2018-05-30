package hitaii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.WhesdtlServicesDaoI;
import hitaii.model.WhesdtlServices;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pservices;
import hitaii.pageModel.PwhesdtlServices;
import hitaii.service.WhesdtlServicesServiceI;
import hitaii.util.BeanUtils;

@Service("whesdtlServicesService")
public class WhesdtlServicesServiceImpl implements WhesdtlServicesServiceI {
	
	private WhesdtlServicesDaoI whesdtlServicesDao;
	
	public WhesdtlServicesDaoI getWhesdtlServicesDao() {
		return whesdtlServicesDao;
	}
	@Autowired
	public void setWhesdtlServicesDao(WhesdtlServicesDaoI whesdtlServicesDao) {
		this.whesdtlServicesDao = whesdtlServicesDao;
	}

	@Override
	public Json updateWhesdtlServices(PwhesdtlServices pwhesdtlServices) {
		Json j = new Json();
		WhesdtlServices whesdtlServices = whesdtlServicesDao.get("from WhesdtlServices where id =:id",pwhesdtlServices);
		if(null != whesdtlServices){
			try {
				Double pay = Double.parseDouble(pwhesdtlServices.getPrice())*(100-Double.parseDouble(pwhesdtlServices.getDiscount()))/100;
				java.text.NumberFormat nf = java.text.NumberFormat.getInstance();   
				nf.setGroupingUsed(false);  
				pwhesdtlServices.setPay(nf.format(pay));
				BeanUtils.copyProperties(pwhesdtlServices, whesdtlServices);
				whesdtlServicesDao.update(whesdtlServices);
				j.setObj(whesdtlServices);
				j.setSuccess(true);
				return j;
			} catch (Exception e) {
				j.setMsg("Can't calculate");
				return j;
			}
		}else{
			j.setMsg("service is not exist");
			return j;
		}
		
	}
	
	@Override
	public Json updateWhesdtlServicesPay(PwhesdtlServices pwhesdtlServices) {
		Json j = new Json();
		WhesdtlServices whesdtlServices = whesdtlServicesDao.get("from WhesdtlServices where id =:id",pwhesdtlServices);
		if(null != whesdtlServices){
			try {
				Double discount = (Double.parseDouble(pwhesdtlServices.getPrice())-Double.parseDouble(pwhesdtlServices.getPay()))/Double.parseDouble(pwhesdtlServices.getPrice())*100;
				long editDiscount = Math.round(discount);
				pwhesdtlServices.setDiscount(String.valueOf(editDiscount));
				BeanUtils.copyProperties(pwhesdtlServices, whesdtlServices);
				whesdtlServicesDao.update(whesdtlServices);
				j.setObj(whesdtlServices);
				j.setSuccess(true);
				return j;
			} catch (Exception e) {
				j.setMsg("Can't calculate");
				return j;
			}
		}else{
			j.setMsg("service is not exist");
			return j;
		}
	}
	
	@Override
	public WhesdtlServices updateWhesdtlServicesPayState(PwhesdtlServices pwhesdtlServices) {
		WhesdtlServices whesdtlServices = whesdtlServicesDao.get("from WhesdtlServices where id =:id",pwhesdtlServices);
		if(null != whesdtlServices){
			if(pwhesdtlServices.getPaystate().equals("1")){
				pwhesdtlServices.setPaystate("2");
				BeanUtils.copyProperties(pwhesdtlServices, whesdtlServices);
				whesdtlServicesDao.update(whesdtlServices);
			}else if(pwhesdtlServices.getPaystate().equals("2")){
				pwhesdtlServices.setPaystate("1");
				BeanUtils.copyProperties(pwhesdtlServices, whesdtlServices);
				whesdtlServicesDao.update(whesdtlServices);
			}
		}
		return whesdtlServices;
	}
	
	@Override
	public List<WhesdtlServices> findWhesdtlServicesById(Pservices pservices) {
		return whesdtlServicesDao.find("from WhesdtlServices where servicesId =:id", pservices);
	}
	
	@Override
	public List<WhesdtlServices> findServicesByWhesdtlId(PwhesdtlServices pwhesdtlServices) {
		return whesdtlServicesDao.find("from WhesdtlServices where whesdtlId =:whesdtlId", pwhesdtlServices);
	}

}
