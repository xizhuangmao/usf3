package hitaii.service;

import hitaii.pageModel.Presource;
import hitaii.pageModel.Tree;

import java.util.List;

public interface ResourceServiceI {
	
	/*
	 * 增加一个资源
	 */
	public Presource save(Presource resource );
	
	/*
	 * 获取一个资源
	 */
	public Presource get(Presource resource);

	/*
	 * 获取所有资源树列表
	 */
	public List<Presource> treeGrid(Presource resource);
	
	/*
	 * 删除一个资源
	 */
	public void remove(Presource resource);

	/*
	 * 编辑一个资源
	 */
	public void edit(Presource resource);

	/*
	 * 获取所有资源树
	 */
	public List<Tree> tree(Presource resource);

	
}
