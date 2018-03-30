package com.septinary.common.web.general.runtime.springmvc;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.septinary.common.core.exception.ConfigError;
import com.septinary.common.core.util.IErrorGetter;
import com.septinary.common.core.util.IErroringable;
import com.septinary.common.core.util.ILoggingable;
import com.septinary.common.core.util.Loggerable;
import com.septinary.common.type.IFieldValue;
import com.septinary.common.type.Result;
import com.septinary.common.util.Assert;
import com.septinary.common.util.StringUtil;

/**
 * 七进制通用错误定义配置读取类
 * @Filename: com.septinary.common.web.general.runtime.springmvc.SeptinaryErrorer.java of the project [com.septinary.common.web]
 *     @Type: SeptinaryErrorer
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年7月26日 下午5:51:19
 */
public class SeptinaryErrorer {

	private static ILoggingable logger = Loggerable.GetLogger(SeptinaryField.class);
	
	//读取器
	private IErrorGetter getter = null;
	//读取缓存
	private HashMap<String,HashMap<String,Result>> errors = new HashMap<String,HashMap<String,Result>>();
	
	//单件
	private SeptinaryErrorer(){}
	private static SeptinaryErrorer septinaryErrorer = new SeptinaryErrorer();

	private IErrorGetter getGetter() {
		if( null==this.getter ) {
			this.setGetter( (IErrorGetter)ContextLoader.getCurrentWebApplicationContext().getBean("errorService") );
		}
		Assert.NotNull(this.getter, "Dose not found the bean: errorService on the current context: " + ContextLoader.getCurrentWebApplicationContext().getApplicationName());
		return getter;
	}

	private void setGetter(IErrorGetter getter) {
		this.getter = getter;
	}
	
	private HashMap<String,Result> put(IFieldValue type, List<Result> list) {
		HashMap<String,Result> hm = new HashMap<String,Result>();

		logger.trace("Cache the error codes of type[" + (null==type.getField()?"":(type.getField().getName()+".")) + "(" + type.getValue() + ":" + type.getKey() + ") - " + type.getParse() + "]:");
		for(Result code: list) {
			hm.put(code.getCode(), code);
			logger.trace("\t" + StringUtil.Middle(code.getCode(),8) + "\t" + StringUtil.Left(code.getInfo(),32) + (StringUtil.Invalid(code.getMemo()) ? "" : "\t#"+code.getMemo()));
		}
		this.errors.put(type.getValue(), hm);
		
		return hm;
	}
	
	private Result get(IFieldValue type, String errorcode) {
		HashMap<String,Result> list = this.errors.get(type.getValue());
		
		if(null==list) {
			List<Result> codes = this.getGetter().get(type);
			if(null==codes || codes.isEmpty()) throw new ConfigError("Has not found the error type:[" + type.getValue() + "], please check up the configuration!");
			list = this.put(type,codes);
		}
		
		Result code = list.get(errorcode);
		if(null==code) throw new ConfigError("Has not found the error[" + type.getValue() + "] code:[" + errorcode + "], please check up the configuration!");
		return code;
	}
	
	static public IErroringable GetErrorer(final IFieldValue type) {
		return new IErroringable(){
			@Override public Result error(String errorcode) {
				return septinaryErrorer.get(type, errorcode);
			}
		};
	}
}
