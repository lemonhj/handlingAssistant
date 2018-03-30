package com.septinary.common.core.util;

import com.septinary.common.core.type.ConfigPriorityLevel;
import com.septinary.common.type.IFieldValue;
import com.septinary.common.type.Value;

/**
 * 配置项
 * @Filename: com.septinary.common.core.util.ConfigItem.java of the project [com.septinary.common]
 *     @Type: ConfigItem
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年7月8日 下午5:35:09
 */
public class ConfigItem extends Value<Object> {
	private ConfigPriorityLevel level;

	private String path;
	
	public ConfigItem(){}
	
	public ConfigItem(Object value, IFieldValue type){
		super(value, type);
	}
	
	public ConfigItem(String path, Object value, IFieldValue type){
		super(value, type);
		this.path = path;
	}
	
	public ConfigItem(ConfigPriorityLevel level, String path, Object value, IFieldValue type){
		super(value, type);
		this.level = level;
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ConfigPriorityLevel getLevel() {
		return level;
	}

	public void setLevel(ConfigPriorityLevel level) {
		this.level = level;
	}

}
