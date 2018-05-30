package hitaii.dao.impl;

import hitaii.dao.CountryDaoI;
import hitaii.model.Country;

import org.springframework.stereotype.Repository;

@Repository("countryDao")
public class CountryDaoImpl extends BaseDaoImpl<Country> implements CountryDaoI{

}
