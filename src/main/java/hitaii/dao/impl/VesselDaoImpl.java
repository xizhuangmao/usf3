package hitaii.dao.impl;


import org.springframework.stereotype.Repository;

import hitaii.dao.VesselDaoI;
import hitaii.model.Vessel;


@Repository("vesselDao")
public class VesselDaoImpl extends BaseDaoImpl<Vessel> implements VesselDaoI {



}
