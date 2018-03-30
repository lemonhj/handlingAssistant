package com.beigebigdata.bdCourt.ca.admin.common.utils;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * 读取资源文件
 * @author lv.jinping
 *
 */
public final class PropertiesConfig {

	//正式环境
	public static final String ROPERTIES_FILEPATH=  Thread.currentThread().getContextClassLoader().getResource("").toString();


	/**
	 * 获取配置文件值
	 * @param key
	 * @return
	 */
	public static String getConfigKeyValue(String key){
		String osName = System.getProperties().getProperty("os.name");
		String path= ROPERTIES_FILEPATH+"config.properties";
		 if("Linux".equals(osName)){
		 	path = path.replace("file:", "");  
            path = path.replace('\\', '/'); 
	      }else{
        	 path = path.replace("file:/", "");  
        	 path = path.replace('/', '\\');
	      }
		String content = PropertiesUtils.getValue(path, key);
		try {
			if (StringUtils.isNotEmpty(content)) {
				content=new String(content.getBytes("iso-8859-1"),"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return content;
	}
	

	
	
	public static void main(String[] args) throws Exception {
		System.err.println(getConfigKeyValue("img_bannerfile_path"));
	}
}