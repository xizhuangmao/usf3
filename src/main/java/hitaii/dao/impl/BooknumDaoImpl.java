package hitaii.dao.impl;

import hitaii.dao.BooknumDaoI;
import hitaii.model.Booknum;

import org.springframework.stereotype.Repository;

@Repository("booknumDao")
public class BooknumDaoImpl extends BaseDaoImpl<Booknum> implements BooknumDaoI{
	
}
