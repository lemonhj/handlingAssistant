package com.septinary.common.manager;

import com.septinary.common.core.exception.RuntimeStateError;
import com.septinary.common.core.util.IResourceMessagingable;

/**
 * 资源配置管理器
 * @Filename: com.septinary.common.manager.ResourceMessagerManager.java of the project [com.septinary.common]
 *     @Type: ResourceMessagerManager
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月10日上午10:32:20
 *
 */
public class ResourceMessagerManager {

    /**
     * 内部接口：消息工厂
     */
    public interface IMessagerFactory {
        public IResourceMessagingable getMessager();
    }
    
    /**
     * 单件模式
     */
    private ResourceMessagerManager() {}
    private static class SingletonInstanceHolder {
        static private ResourceMessagerManager instance = new ResourceMessagerManager();
    }
    static public ResourceMessagerManager Instance() {
        return SingletonInstanceHolder.instance;
    }

    //messager工厂
    private IMessagerFactory messagerFactory = null;

    //获取messager
    public IResourceMessagingable getMessager() {
        if(null==this.messagerFactory) throw new RuntimeStateError("Not found valid messagerFactory setting in sigleton:"+this.getClass().getName());
        return this.messagerFactory.getMessager();
    }
    //设置messager工厂
    public void setMessagerFactory(IMessagerFactory messagerFactory) {
        this.messagerFactory = messagerFactory;
    }
}
