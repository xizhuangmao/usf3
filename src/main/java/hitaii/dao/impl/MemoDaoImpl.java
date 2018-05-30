package hitaii.dao.impl;

import org.springframework.stereotype.Repository;

import hitaii.dao.MemoDaoI;
import hitaii.model.Memo;

@Repository("memoDao")
public class MemoDaoImpl extends BaseDaoImpl<Memo> implements MemoDaoI {

}
