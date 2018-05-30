package hitaii.dao.impl;

import hitaii.dao.CustomerDaoI;
import hitaii.model.Customer;
import org.springframework.stereotype.Repository;


@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDaoI{

}
