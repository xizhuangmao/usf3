package hitaii.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.ResourceDaoI;
import hitaii.model.Resource;
import hitaii.pageModel.Tree;
import hitaii.pageModel.Presource;
import hitaii.service.ResourceServiceI;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceServiceI {

	private static final Logger logger = Logger
			.getLogger(ResourceServiceImpl.class);

	private ResourceDaoI resourceDao;

	public ResourceDaoI getResourceDao() {
		return resourceDao;
	}

	@Autowired
	public void setResourceDao(ResourceDaoI resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Override
	public Presource save(Presource resource) {
		Resource tresourse = new Resource();
		BeanUtils.copyProperties(resource, tresourse);
		tresourse.setId(UUID.randomUUID().toString());
		resourceDao.save(tresourse);
		return resource;
	}

	@Override
	public List<Presource> treeGrid(Presource resource) {

		List<Resource> tl = new ArrayList<Resource>();
		List<Presource> ul = new ArrayList<Presource>();
		String hql = "from Resource t ";

		tl = resourceDao.find(hql);

		changeModel(tl, ul);

		return ul;
	}

	@Override
	public void remove(Presource resource) {
		
		Resource tresource = resourceDao.get(Resource.class, resource.getId());
		
		if(null!=tresource){
			resourceDao.delete(tresource);
		}
		
		
	}

	@Override
	public void edit(Presource resource) {

		Resource tresource = new Resource();
		BeanUtils.copyProperties(resource, tresource);
		resourceDao.update(tresource);

	}

	private void changeModel(List<Resource> tl, List<Presource> ul) {
		if (null != tl && tl.size() > 0) {
			for (Resource t : tl) {
				Presource u = new Presource();
				BeanUtils.copyProperties(t, u);

				ul.add(u);
			}

		}
	}

	@Override
	public List<Tree> tree(Presource resource) {

		List<Resource> resourceList = new ArrayList<Resource>();
		List<Tree> treeList = new ArrayList<Tree>();
		String hql = "from Resource t ";

		resourceList = resourceDao.find(hql);

		if (null != resourceList && resourceList.size() > 0) {
			for (Resource t : resourceList) {
				Tree tree = new Tree();
				BeanUtils.copyProperties(t, tree);
				tree.setText(t.getName());
				treeList.add(tree);
			}

		}

		return treeList;
	}

	@Override
	public Presource get(Presource resource) {
		
		Resource tresource =resourceDao.get(" from Resource t where t.id=:id ", resource);
		
		BeanUtils.copyProperties(tresource, resource);
		
		return resource;
	}

}
