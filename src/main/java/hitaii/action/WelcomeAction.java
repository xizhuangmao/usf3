package hitaii.action;

import java.util.HashMap;
import java.util.Map;

import hitaii.service.WhesdtlServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *	by qc  2016.10.25
 *  首页Action
 */
@Namespace("/")
@Action(value = "welcomeAction")
public class WelcomeAction extends BaseAction{
	
	private WhesdtlServiceI whesdtlService;
	
	public WhesdtlServiceI getWhesdtlService() {
		return whesdtlService;
	}
	@Autowired
	public void setWhesdtlService(WhesdtlServiceI whesdtlService) {
		this.whesdtlService = whesdtlService;
	}

	public void findWelcomeInfo(){
		Map<String, Long> map = new HashMap<String, Long>();
		Long booknumCount = whesdtlService.getBooknumVehicleCount();
		Long whesdtlCount = whesdtlService.getCountPreAlertWhesdtl();
		Long userCount = whesdtlService.findVehicleInfo();
		map.put("booknumCount", booknumCount);
		map.put("whesdtlCount", whesdtlCount);
		map.put("userCount", userCount);
		super.writeJson(map);
	}
}
