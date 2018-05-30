package hitaii.dao.impl;

import org.springframework.stereotype.Repository;

import hitaii.dao.TruckDaoI;
import hitaii.model.Truck;
@Repository("truckDao")
public class TruckDaoImpl extends BaseDaoImpl<Truck> implements TruckDaoI{

}
