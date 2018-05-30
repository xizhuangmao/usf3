package hitaii.dao.impl;

import hitaii.dao.DockDaoI;
import hitaii.model.Dock;

import org.springframework.stereotype.Repository;

@Repository("dockDao")
public class DockDaoImpl extends BaseDaoImpl<Dock> implements DockDaoI{

}
