package hitaii.dao.impl;

import org.springframework.stereotype.Repository;

import hitaii.dao.OrdersDaoI;
import hitaii.model.Orders;

/*
 * @author zw 
 * 
 * 时间：20160108
 * 
 * 示例
 * 客户订单DaoImpl
 */
@Repository("ordersDao")
public class OrdersDaoImpl extends BaseDaoImpl<Orders> implements OrdersDaoI{

}
