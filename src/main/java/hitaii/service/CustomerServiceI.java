package hitaii.service;

import hitaii.pageModel.Pcustomer;

import java.util.List;
import java.util.Map;

public interface CustomerServiceI {
	/**
	 * 获取Customer列表
	 */
	List<Map<String, String>> findCustomerName();

}
