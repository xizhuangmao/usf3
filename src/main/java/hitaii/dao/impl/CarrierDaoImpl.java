package hitaii.dao.impl;

import org.springframework.stereotype.Repository;

import hitaii.dao.CarrierDaoI;
import hitaii.model.Carrier;

@Repository("carrierDao")
public class CarrierDaoImpl extends BaseDaoImpl<Carrier> implements CarrierDaoI {


}
