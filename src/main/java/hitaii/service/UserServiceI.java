package hitaii.service;


import java.util.List;
import java.util.Map;

import hitaii.model.Users;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Puser;
import hitaii.pageModel.Pwhesdtl;


public interface UserServiceI {

	/*
	 * 保存一个用户
	 */
	public Puser save (Puser user ) throws Exception;
	
	/*
	 * 用户登录
	 */
	public Users login(Puser user);

	/*
	 * 获取用户datagrid
	 */
	public DataGrid dataGrid(Puser user);

	/*
	 * 删除一个用户
	 */
	public void remove(Puser user);

	/*
	 * 编辑一个用户
	 */
	public Puser edit(Puser user) throws Exception;
	
	/*
	 * 获取一个用户
	 */
	public Puser get(Puser user) throws Exception;
	
	/*
	 * 1、编辑用户角色
	 */
	public Puser editRole(Puser user) throws Exception;
	
	/*
	 * 1.5、编辑用户公司
	 */
	public Puser editCompany(Puser user) throws Exception;
	
	/*
	 * 2、编辑用户仓库
	 */
	public Puser editWhes(Puser user) throws Exception;
	
	/*
	 * 3、编辑用户nvocc
	 */
	public Puser editNvocc(Puser user) throws Exception;
	
	/*
	 * 4、编辑用户航运公司
	 */
	public Puser editCarrier(Puser user) throws Exception;
	
	/*
	 * 5、编辑用户卡车公司
	 */
	public Puser editTruck(Puser user) throws Exception;

	//根据name查询customer
	public Users getCustomer(Pwhesdtl pwhesdtl);
	
	//查询customer
	public List<Users> getCustomerName();
	

}
