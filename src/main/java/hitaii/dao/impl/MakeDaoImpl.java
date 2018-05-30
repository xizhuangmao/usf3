package hitaii.dao.impl;

import org.springframework.stereotype.Repository;

import hitaii.dao.MakeDaoI;
import hitaii.model.Make;

@Repository("makeDao")
public class MakeDaoImpl extends BaseDaoImpl<Make> implements MakeDaoI {


}
