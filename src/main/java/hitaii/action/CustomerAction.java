package hitaii.action;

import hitaii.pageModel.Pcustomer;
import hitaii.service.CustomerServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 *	@author mh
 * 
 * 时间：20160108
 * 
 * 客户Action
 */

@Namespace("/")
@Action(value = "customerAction")
public class CustomerAction extends BaseAction implements ModelDriven<Pcustomer>{
	private Pcustomer customer = new Pcustomer();
	
	private CustomerServiceI customerService;

	public CustomerServiceI getCustomerService() {
		return customerService;
	}
	@Autowired
	public void setCustomerService(CustomerServiceI customerService) {
		this.customerService = customerService;
	}

	@Override
	public Pcustomer getModel() {
		return customer;
	}
	
	/**
	 * 获取Customer列表
	 */
	public void getCustomerName(){
		super.writeJson(customerService.findCustomerName());
	}
	
}
