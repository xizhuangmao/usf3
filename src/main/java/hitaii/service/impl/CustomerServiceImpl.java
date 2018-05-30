package hitaii.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hitaii.dao.CustomerDaoI;
import hitaii.model.Customer;
import hitaii.model.Nvocc;
import hitaii.model.Users;
import hitaii.model.Whesdtl;
import hitaii.pageModel.Pcustomer;
import hitaii.pageModel.Pwhesdtl;
import hitaii.service.CustomerServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("customerService")
public class CustomerServiceImpl implements CustomerServiceI{
	
	private CustomerDaoI customerDao;
	
	public CustomerDaoI getCustomerDao() {
		return customerDao;
	}
	@Autowired
	public void setCustomerDao(CustomerDaoI customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public List<Map<String, String>> findCustomerName() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>> ();
		String hql = "from Customer order by fullname";
		List<Customer> customers =customerDao.find(hql);
		if(customers.size()>0){
			Map<String, String> mapAll = new HashMap<String,String>();
			mapAll.put("fullname", "Please Select");
			mapAll.put("id", "");
			list.add(mapAll);
			for(int i =0;i<customers.size();i++){
				Customer customer = customers.get(i);
				Map<String, String> map = new HashMap<String,String>();
				map.put("fullname",  customer.getFullname());
				map.put("id", customer.getId());
				list.add(map);
			}
		}
		return list;
	}
	
}
