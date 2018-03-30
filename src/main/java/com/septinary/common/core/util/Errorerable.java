package com.septinary.common.core.util;

import com.septinary.common.manager.ErrorManager;
import com.septinary.common.type.IFieldValue;
import com.septinary.common.type.Result;
import com.septinary.common.util.Assert;

/**
 * 错误代码读取适配器
 * @Filename: com.septinary.common.core.util.Errorerable.java of the project [com.septinary.common]
 *     @Type: Errorerable
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年7月15日 下午6:17:52
 */
public class Errorerable {

	private IErroringable errorer = null;

    public Errorerable(IFieldValue type) {
    	Assert.NotNull(type, "The type of the errorer must not be null!");
    	
        System.out.println("Getting errorer from sigleton:errorManager for " + (null==type.getField()?"":(type.getField().getName()+".")) + "("+type.getValue()+":"+type.getKey()+") - "+type.getParse()+"' ...");
        this.errorer = ErrorManager.Instance().getErrorer(type);
    }
    
    //静态错误读取器
    public static IErroringable GetErrorer(IFieldValue type) {
    	return ErrorManager.Instance().getErrorer(type);
    }
    
    public Result error(String code) {
    	return this.errorer.error(code);
    }
}
