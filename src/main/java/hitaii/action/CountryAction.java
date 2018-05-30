package hitaii.action;

import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pcountry;
import hitaii.service.CountryServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;

/**
 *	@author mh
 * 
 * 时间：20160108
 * 
 * 国家Action
 */
@Namespace("/")
@Action(value = "countryAction")
public class CountryAction extends BaseAction implements ModelDriven<Pcountry>{
	private Pcountry country=new Pcountry();
	@Override
	public Pcountry getModel() {
		return country;
	}
	
	private CountryServiceI countryService;
	public CountryServiceI getCountryService() {
		return countryService;
	}
	@Autowired
	public void setCountryService(CountryServiceI countryService) {
		this.countryService = countryService;
	}
	
	/**
	 * 返回下拉列表名
	 */
	public void getCountryName(){
		super.writeJson(countryService.findCountryName());	
	}
	
	/**
	 * 分页查询所有数据  country DataGrid页面
	 */
	public void getCountryDatagrid(){
		DataGrid d = countryService.getCountryDatagrid(country);
		super.writeJson(d);
	}
	
	/**
	 * 保存一个country
	 */
	public void addCountry(){
		Json j = countryService.addCountry(country);
		super.writeJson(j);
	}
	
	//根据id 返回Country页面对象
	public void findCountryById(){
		Json j = countryService.findCountryById(country);
		super.writeJson(j);
	}
	
	/**
	 * Country页面 删除一个对象
	 */
	public void deleteCountry(){
		Json j = new Json();
		boolean b = countryService.deleteCountry(country);
		if(b){
			j.setSuccess(true);
			j.setMsg("delect Country success...");
		}else{
			j.setSuccess(false);
			j.setMsg("delect Country failed...");
		}
		super.writeJson(j);
	}
	
	/**
	 * 修改make
	 */
	public void editCountry(){
		Json j = countryService.editCountry(country);
		super.writeJson(j);
	}
}
