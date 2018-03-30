package com.septinary.common.manager;

import com.septinary.common.core.exception.RuntimeStateError;
import com.septinary.common.core.util.IConfigGetter;
import com.septinary.common.core.util.IConfigSetter;
import com.septinary.common.core.util.IConfiggingable;

/**
 * 配置管理器
 * @Filename: com.septinary.common.manager.ConfiggerManager.java of the project [com.septinary.common]
 *     @Type: ConfiggerManager
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月3日下午3:29:01
 *
 */
public class ConfiggerManager {

    /**
     * 内部接口：日志工厂
     */
    public interface IConfiggerFactory {
        public IConfiggingable getConfigger();
    }
    public interface IConfigReaderFactory {
        public IConfigGetter getReader();
    }
    public interface IConfigWriterFactory {
        public IConfigSetter getWriter();
    }

    /**
     * 单件模式
     */
    private ConfiggerManager() {}
    private static class SingletonInstanceHolder {
        static private ConfiggerManager instance = new ConfiggerManager();
    }
    static public ConfiggerManager Instance() {
        return SingletonInstanceHolder.instance;
    }

    //configger工厂
    private IConfiggerFactory configgerFactory = null;
    private IConfigReaderFactory configReaderFactory = null;
    private IConfigWriterFactory configWriterFactory = null;

    //获取configger
    public IConfiggingable getConfigger() {
        if(null==this.configgerFactory) throw new RuntimeStateError("Not found valid configgerFactory setting in sigleton:ConfiggerManager");
        return this.configgerFactory.getConfigger();
    }
    public IConfigGetter getReader() {
        if(null==this.configReaderFactory) throw new RuntimeStateError("Not found valid configReaderFactory setting in sigleton:ConfiggerManager");
        return this.configReaderFactory.getReader();
    }
    public IConfigSetter getWriter() {
        if(null==this.configWriterFactory) throw new RuntimeStateError("Not found valid configWriterFactory setting in sigleton:ConfiggerManager");
        return this.configWriterFactory.getWriter();
    }

    //设置configger工厂
    public void setConfiggerFactory(IConfiggerFactory configgerFactory) {
        this.configgerFactory = configgerFactory;
    }
    public void setConfigReaderFactory(IConfigReaderFactory configReaderFactory) {
        this.configReaderFactory = configReaderFactory;
    }
    public void setConfigWriterFactory(IConfigWriterFactory configWriterFactory) {
        this.configWriterFactory = configWriterFactory;
    }
}
