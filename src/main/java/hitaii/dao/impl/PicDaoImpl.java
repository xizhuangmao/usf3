package hitaii.dao.impl;

import org.springframework.stereotype.Repository;

import hitaii.dao.PicDaoI;
import hitaii.model.Pic;

@Repository("picDao")
public class PicDaoImpl extends BaseDaoImpl<Pic>  implements PicDaoI {

}
