package com.septinary.common.manager;

import com.septinary.common.core.exception.RuntimeStateError;
import com.septinary.common.core.util.IFieldingable;

/**
 * 枚举字段配置管理器
 * @Filename: com.septinary.common.manager.FielderManager.java of the project [com.septinary.common]
 *     @Type: FielderManager
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午9:30:46
 *
 */
public class FielderManager {
	
	/**
	 * 内部接口: 字段工厂
	 */
	public interface IFielderFactory {
		public IFieldingable getFielder();
	}
	
    /**
     * 单件模式
     */
    private FielderManager() {}
    private static class SingletonInstanceHolder {
        static private FielderManager instance = new FielderManager();
    }
    static public FielderManager Instance() {
        return SingletonInstanceHolder.instance;
    }

    //fielder工厂
    private IFielderFactory fielderFactory = null;
    
    //获取fielder
    public IFieldingable getFielder() throws RuntimeStateError {
    	if(null==this.fielderFactory) throw new RuntimeStateError("Not found valid fielderFactory setting in sigleton:FielderManager");
    	return this.fielderFactory.getFielder();
    }
    
    //设置fielder工厂
    public void setFielderFactory(IFielderFactory fielderFactory) {
    	this.fielderFactory = fielderFactory;
    }

}
