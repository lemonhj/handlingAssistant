package com.septinary.common.web.general.runtime.springmvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.context.ContextLoader;

import com.septinary.common.core.exception.ConfigError;
import com.septinary.common.core.util.ConfigFrom;
import com.septinary.common.core.util.ConfigItem;
import com.septinary.common.core.util.Fielderable;
import com.septinary.common.core.util.IConfigGetter;
import com.septinary.common.core.util.IConfigSetter;
import com.septinary.common.core.util.ILoadable;
import com.septinary.common.core.util.IPostable;
import com.septinary.common.type.IField;
import com.septinary.common.type.IFieldValue;
import com.septinary.common.type.Mark;
import com.septinary.common.type.Value;
import com.septinary.common.util.FileUtil;
import com.septinary.common.util.ISetter;
import com.septinary.common.util.StringUtil;
import com.septinary.common.util.crypt.MD5;

/**
 * 七进制通用配置读取类
 * @Filename: com.septinary.common.web.spingmvc.runtime.SeptinaryConfig.java of the project [com.septinary.common.web]
 *     @Type: SeptinaryConfig
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月24日下午10:57:49
 *
 *	目前只是从数据库读取。
 */
public class SeptinaryConfig {
	//配置读取器集合
	static private HashMap<String,MyConfigGetter> readers = new HashMap<String,MyConfigGetter>();

	static public MyConfigGetter GetReader(String pathes, String delimiter) {
		if( null==pathes ) return null;
		
		//缺省：;
		delimiter = StringUtil.Invalid(delimiter) ? FileUtil.FOLDERS_SEPARATOR : delimiter;
		
		//每个指定的配置路径均会读取其父级路径中的配置项	注：使用Set保证了读取路径不重复，使用LinkedHashSet保证按照插入顺序加载！
		Set<Mark<Boolean,String>> set = new LinkedHashSet<Mark<Boolean,String>>();
		for(String path: pathes.split(delimiter)) {
			if( StringUtil.Invalid(path) ) continue;
			String[] tmp = FileUtil.SplitPath(path, "[^\\w\\-]+");
			int len = tmp.length;
			int idx = 0;
			for(String section: tmp) {
				idx++;
				if( StringUtil.Invalid(section) ) continue;
				boolean indicated = (idx==len);
				Mark<Boolean,String> mark = new Mark<Boolean,String>(indicated, section.trim()){
					@Override public String toString() { return "("+this.getMark()+")" + this.getValue(); }
					@SuppressWarnings("unused") public boolean equals(Mark<Boolean,String> obj) { return super.getValue().equals(obj.getValue()); }
				};
				//只有指明的配置路径需要加载全部加载项，其余那些自动加载的父级路径只能加载允许被继承的配置项
				if(indicated) set.remove(mark);
				set.add(mark);
			}
		}
		
		//从内存中读取
		List<Mark<Boolean,String>> list = new ArrayList<Mark<Boolean,String>>(set);
		String pathlist = StringUtil.Implode(list, delimiter);
		String key = new MD5().encrypt(pathlist, null);
		if( !readers.containsKey(key) ) {
			System.out.println("配置路径："+pathlist);
			readers.put(key, new MyConfigGetter(list));
		}
		
		return readers.get(key);
	}
	static public MyConfigGetter GetReader(String pathes) {
		return GetReader(pathes, ",");
	}
	
	/**
	 * 内置的配置读取器
	 * @Filename: com.septinary.common.web.spingmvc.runtime.SeptinaryConfig.java of the project [com.septinary.common.web]
	 *     @Type: MyConfigGetter
	 *     @Desc: TODO
	 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
	 *  @Created: 2016年7月5日 下午7:54:39
	 */
	static public class MyConfigGetter implements IConfigGetter {
		private List<ConfigFrom> configPool = null;
		
		public MyConfigGetter(List<Mark<Boolean,String>> pathes) {
			this.init(pathes);
		}
		
		@SuppressWarnings("unchecked")
		private void init(List<Mark<Boolean,String>> pathes) {
			
			final IField field = Fielderable.GetFielder().field("ConfigPriorityLevel");
			
			this.configPool = ((ILoadable<ConfigFrom,List<Mark<Boolean,String>>>)ContextLoader.getCurrentWebApplicationContext().getBean("applicationContext")).load(pathes);
			Collections.sort(this.configPool, new Comparator<ConfigFrom>(){
				@Override public int compare(ConfigFrom o1, ConfigFrom o2) {
					if(null==o1) return -1;
					IFieldValue fv1 = field.value(o1.getConfigLevel().name());
					if(null==fv1) return -1;
					if(null==o2) return 1;
					IFieldValue fv2 = field.value(o2.getConfigLevel().name());
					if(null==fv2) return 1;
					return field.value(o1.getConfigLevel().name()).getValue().compareTo(field.value(o2.getConfigLevel().name()).getValue());
				}}
			);
		}

		@Override
		public Value<Object> get(String key) {
			ConfigError error = null;
			for(ConfigFrom from: this.configPool) {
				try {
					Value<Object> item = from.get(key);
					if(null!=item) return item;
				} catch (ConfigError e) {
					error = e;
					continue;
				}
			}
			if(null!=error) throw new ConfigError("Has not found the config item:[" + key + "], please check up the configuration!");
			return null;
		}
	}

	/**
	 * 内置的配置写入器
	 * @Filename: com.septinary.common.web.spingmvc.runtime.SeptinaryConfig.java of the project [com.septinary.common.web]
	 *     @Type: MyConfigSetter
	 *     @Desc: TODO
	 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
	 *  @Created: 2016年7月8日 下午5:52:13
	 */
	static public class MyConfigSetter implements IConfigSetter {
		private IPostable<Integer,ConfigItem> db = null;
		
		public MyConfigSetter() {}
		
		@SuppressWarnings("unchecked")
		private void init() {
			if( null==this.db ) {
				// TODO 目前的这种方式存在缺陷：在WEB容器启动到某个阶段之前ContextLoader.getCurrentWebApplicationContext()是无效的！！！
				this.db = (IPostable<Integer,ConfigItem>)ContextLoader.getCurrentWebApplicationContext().getBean("configService");
			}
		}

		@Override
		public ISetter<String, ConfigItem> set(String key, ConfigItem value) {
			this.init();
			this.db.post(Arrays.asList(value));
			return this;
		}

	}
}
