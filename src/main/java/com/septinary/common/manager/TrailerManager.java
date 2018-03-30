package com.septinary.common.manager;

import java.util.HashMap;

import com.septinary.common.core.exception.RuntimeStateError;
import com.septinary.common.core.util.ITrailingable;

/**
 * 轨迹跟踪管理器
 * @Filename: com.septinary.common.manager.TrailerManager.java of the project [com.septinary.common]
 *     @Type: TrailerManager
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月2日 下午4:33:18
 */
public class TrailerManager {

    /**
     * 内部接口：轨迹工厂
     */
    public interface ITrailerFactory {
        public ITrailingable getTrailer(Object holder);
    }

    /**
     * 单件模式
     */
    private TrailerManager() {}
    private static class SingletonInstanceHolder {
        static private TrailerManager instance = new TrailerManager();
    }
    static public TrailerManager Instance() {
        return SingletonInstanceHolder.instance;
    }

    //trailer缓存
    private HashMap<String,ITrailingable> trailers = new HashMap<String,ITrailingable>();
    //trailer工厂
    private ITrailerFactory trailerFactory = null;

    //获取trailer
    public ITrailingable getTrailer(Object holder) throws RuntimeStateError {
        ITrailingable trailer = this.trailers.get(holder.getClass().getName());
        if(null!=trailer) return trailer;

        if(null==this.trailerFactory) throw new RuntimeStateError("Not found valid trailerFactory setting in sigleton:TrailerManager");
        trailer = this.trailerFactory.getTrailer(holder.getClass());
        this.trailers.put(holder.getClass().getName(), trailer);
        return trailer;
    }

    //设置trailer工厂
    public void setTrailerFactory(ITrailerFactory trailerFactory) {
        this.trailerFactory = trailerFactory;
    }
}
