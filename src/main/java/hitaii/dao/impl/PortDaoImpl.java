package hitaii.dao.impl;

import org.springframework.stereotype.Repository;

import hitaii.dao.PortDaoI;
import hitaii.model.Port;

@Repository("portDao")
public class PortDaoImpl extends BaseDaoImpl<Port> implements PortDaoI {


}
