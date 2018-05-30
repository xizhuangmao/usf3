package hitaii.service;

import hitaii.model.Users;
import hitaii.pageModel.Pmenu;

import java.util.List;


public interface MenuServiceI {

	/*
	 * 获取单个节点的树列表
	 */
	public List<Pmenu> getTreeNode(Pmenu menu);
	
	/*
	 * 获取树列表
	 */
	public List<Pmenu> getAllTreeNode(Users users);
	
}
