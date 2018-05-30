package hitaii.dao.impl;

import org.springframework.stereotype.Repository;

import hitaii.dao.CompanyDaoI;
import hitaii.model.Company;

@Repository("bcompanyDao")
public class CompanyDaoImpl extends BaseDaoImpl<Company> implements CompanyDaoI {

}
