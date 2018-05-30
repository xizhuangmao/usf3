package hitaii.service;

import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Prole;


public interface RoleServiceI {
	
	

	/*
	 * 获取datagrid
	 */
	public DataGrid datagrid(Prole prole);
	
	
	/*
	 * 获取所有角色，并把该用户拥有的角色打勾
	 */
	public DataGrid userrolesdatagrid(Prole prole);
	/*
	 * 获取一个角色
	 */
	public Prole get(Prole prole);
	
	/*
	 * 添加一个角色
	 */
	public void add(Prole prole);
	
	/*
	 * 删除一个角色
	 */
	public void delete(Prole prole);
	
	/*
	 * 修改一个角色
	 */
	public void update(Prole prole);
	
	/*
	 * 获取角色下的资源
	 */
	public void roleresource(Prole prole);

}
