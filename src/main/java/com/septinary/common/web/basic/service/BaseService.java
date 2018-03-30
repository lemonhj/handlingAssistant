package com.septinary.common.web.basic.service;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.septinary.common.web.basic.business.Business;
import com.septinary.common.web.basic.dao.IBaseDao;

/**
 * Web 请求处理业务服务抽象基类
 * @Filename: com.septinary.common.web.basic.service.BaseService.java of the project [com.septinary.common.web]
 *     @Type: BaseServiceImpl
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月12日下午12:33:28
 * 
 * @param <T>
 * @param <PK>
 */
abstract public class BaseService<T, PK extends Serializable> extends Business implements IBaseService<T, PK> {

	@SuppressWarnings("unused")
	private IBaseDao<T,PK> baseDao = null;
	
	public void setBaseDao(IBaseDao<T,PK> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public T insert(T entity) {
		// TODO Auto-generated method stub
		this.getBaseDao().insert(entity);
		return entity;
	}

	@Override
	public T insertSelective(T entity) {
		// TODO Auto-generated method stub
		this.getBaseDao().insertSelective(entity);
		return entity;
	}

	@Override
	public List<T> insertList(List<T> entities) {
		// TODO Auto-generated method stub
		this.getBaseDao().insertList(entities);
		return entities;
	}

	@Override
	public T insertUseGeneratedKeys(T entity) {
		// TODO Auto-generated method stub
		this.getBaseDao().insertUseGeneratedKeys(entity);
		return entity;
	}

	@Override
	public int delete(T entity) {
		// TODO Auto-generated method stub
		return this.getBaseDao().delete(entity);
	}

	@Override
	public int deleteByExample(Object example) {
		// TODO Auto-generated method stub
		return this.getBaseDao().deleteByExample(example);
	}

	@Override
	public int deleteByCondition(Object condition) {
		// TODO Auto-generated method stub
		return this.deleteByExample(condition);
	}

	@Override
	public int deleteByPrimaryKey(PK key) {
		// TODO Auto-generated method stub
		return this.getBaseDao().deleteByPrimaryKey(key);
	}

	@Override
	public T updateByExample(T entity, Object example) {
		// TODO Auto-generated method stub
		this.getBaseDao().updateByExample(entity, example);
		return entity;
	}

	@Override
	public T updateByCondition(T record, Object condition) {
		// TODO Auto-generated method stub
		this.updateByExample(record, condition);
		return record;
	}

	@Override
	public T updateByExampleSelective(T entity, Object example) {
		// TODO Auto-generated method stub
		this.getBaseDao().updateByExampleSelective(entity, example);
		return entity;
	}

	@Override
	public T updateByConditionSelective(T record, Object condition) {
		// TODO Auto-generated method stub
		this.updateByExampleSelective(record, condition);
		return record;
	}

	@Override
	public T updateByPrimaryKey(T entity) {
		// TODO Auto-generated method stub
		this.getBaseDao().updateByPrimaryKey(entity);
		return entity;
	}

	@Override
	public T updateByPrimaryKeySelective(T entity) {
		// TODO Auto-generated method stub
		this.getBaseDao().updateByPrimaryKeySelective(entity);
		return entity;
	}

	@Override
	public List<T> select(T entity) {
		// TODO Auto-generated method stub
		return this.getBaseDao().select(entity);
	}

	@Override
	public List<T> selectAll() {
		// TODO Auto-generated method stub
		return this.getBaseDao().selectAll();
	}

	@Override
	public List<T> selectByExample(Object example) {
		// TODO Auto-generated method stub
		return this.getBaseDao().selectByExample(example);
	}

	@Override
	public List<T> selectByCondition(Object condition) {
		// TODO Auto-generated method stub
		return this.selectByExample(condition);
	}

	@Override
	public List<T> selectByExampleAndRowBounds(Object example, RowBounds bounds) {
		// TODO Auto-generated method stub
		return this.getBaseDao().selectByExampleAndRowBounds(example, bounds);
	}

	@Override
	public List<T> selectByConditionAndRowBounds(Object condition, RowBounds rowBounds) {
		// TODO Auto-generated method stub
		return this.selectByExampleAndRowBounds(condition, rowBounds);
	}

	@Override
	public T selectByPrimaryKey(PK key) {
		// TODO Auto-generated method stub
		return this.getBaseDao().selectByPrimaryKey(key);
	}

	@Override
	public List<T> selectByRowBounds(T entity, RowBounds bounds) {
		// TODO Auto-generated method stub
		return this.getBaseDao().selectByRowBounds(entity, bounds);
	}

	@Override
	public int selectCount(T entity) {
		// TODO Auto-generated method stub
		return this.getBaseDao().selectCount(entity);
	}

	@Override
	public int selectCountByExample(Object example) {
		// TODO Auto-generated method stub
		return this.getBaseDao().selectCountByExample(example);
	}

	@Override
	public int selectCountByCondition(Object condition) {
		// TODO Auto-generated method stub
		return this.selectCountByExample(condition);
	}

	@Override
	public T selectOne(T entity) {
		// TODO Auto-generated method stub
		return this.getBaseDao().selectOne(entity);
	}
	
}
