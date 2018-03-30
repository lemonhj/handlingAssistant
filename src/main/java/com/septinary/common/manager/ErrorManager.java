package com.septinary.common.manager;

import java.util.HashMap;

import com.septinary.common.core.exception.RuntimeStateError;
import com.septinary.common.core.util.IErroringable;
import com.septinary.common.type.IFieldValue;

/**
 * 错误代码配置管理器
 * @Filename: com.septinary.common.manager.ErrorManager.java of the project [com.septinary.common]
 *     @Type: ErrorManager
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午9:30:05
 *
 */
public class ErrorManager {
    /**
     * 内部接口：错误代码工厂
     */
    public interface IErrorerFactory {
        public IErroringable getErrorer(IFieldValue type);
    }
    
    /**
     * 单件模式
     */
    private ErrorManager() {}
    private static class SingletonInstanceHolder {
        static private ErrorManager instance = new ErrorManager();
    }
    static public ErrorManager Instance() {
        return SingletonInstanceHolder.instance;
    }


    //errorer缓存
    private HashMap<String,IErroringable> errorers = new HashMap<String,IErroringable>();
    //errorer工厂
    private IErrorerFactory errorerFactory = null;

    //获取errorer
    public IErroringable getErrorer(IFieldValue type) throws RuntimeStateError {
    	IErroringable errorer = this.errorers.get(type.getKey());
        if(null!=errorer) return errorer;

        if(null==this.errorerFactory) throw new RuntimeStateError("Not found valid errorerFactory setting in sigleton:ErrorerManager");
        errorer = this.errorerFactory.getErrorer(type);
        this.errorers.put(type.getKey(), errorer);
        return errorer;
    }

    //设置errorer工厂
    public void setErrorerFactory(IErrorerFactory errorerFactory) {
        this.errorerFactory = errorerFactory;
    }
}
