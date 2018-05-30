package hitaii.dao.impl;

import java.io.Serializable;

import java.util.List;

import org.springframework.stereotype.Repository;

import hitaii.dao.NewsDaoI;
import hitaii.model.News;

@Repository("newsDao")
public class NewsDaoImpl extends BaseDaoImpl<News> implements NewsDaoI {

}
