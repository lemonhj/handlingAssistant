package com.septinary.common.manager;

import java.util.HashMap;

import com.septinary.common.core.exception.RuntimeStateError;
import com.septinary.common.core.util.ILoggingable;

/**
 * 日志输出管理器
 * @Filename: com.septinary.common.manager.LoggerManager.java of the project [com.septinary.common]
 *     @Type: LoggerManager
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月3日上午10:44:47
 *
 */
public class LoggerManager {

    /**
     * 内部接口：日志工厂
     */
    public interface ILoggerFactory {
        public ILoggingable getLogger(Class<?> clazz);
    }

    /**
     * 单件模式
     */
    private LoggerManager() {}
    private static class SingletonInstanceHolder {
        static private LoggerManager instance = new LoggerManager();
    }
    static public LoggerManager Instance() {
        return SingletonInstanceHolder.instance;
    }

    //logger缓存
    private HashMap<String,ILoggingable> loggers = new HashMap<String,ILoggingable>();
    //logger工厂
    private ILoggerFactory loggerFactory = null;

    //获取logger
    public ILoggingable getLogger(Class<?> clazz) throws RuntimeStateError {
        ILoggingable logger = this.loggers.get(clazz.getName());
        if(null!=logger) return logger;

        if(null==this.loggerFactory) throw new RuntimeStateError("Not found valid loggerFactory setting in sigleton:LoggerManager");
        logger = this.loggerFactory.getLogger(clazz);
        this.loggers.put(clazz.getName(), logger);
        return logger;
    }

    //设置logger工厂
    public void setLoggerFactory(ILoggerFactory loggerFactory) {
        this.loggerFactory = loggerFactory;
    }
}
