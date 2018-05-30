package hitaii.dao.impl;

import org.springframework.stereotype.Repository;

import hitaii.dao.FirstPageDaoI;
import hitaii.model.Firstpage;

@Repository("firstPageDao")
public class FirstPageDaoImpl extends BaseDaoImpl<Firstpage> implements FirstPageDaoI {

}
