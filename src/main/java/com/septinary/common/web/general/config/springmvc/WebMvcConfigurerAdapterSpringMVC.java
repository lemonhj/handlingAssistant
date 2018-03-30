package com.septinary.common.web.general.config.springmvc;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * SpringMVC配置适配器
 * @Filename: com.septinary.common.web.general.config.springmvc.WebMvcConfigurerAdapterSpringMVC.java of the project [com.septinary.common.web.general]
 *     @Type: WebMvcConfigurerAdapterSpringMVC
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年9月10日 下午12:02:52
 *  
 *  @version 1.0 支持跨域（注意：暂未引入容器！）
 */
//@Configuration	//将此类置于自动扫描目录，将自动引入它！
public class WebMvcConfigurerAdapterSpringMVC extends WebMvcConfigurerAdapter {
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins("http://domain2.com")
            .allowedMethods("PUT", "DELETE")
            .allowedHeaders("header1", "header2", "header3")
            .exposedHeaders("header1", "header2")
            .allowCredentials(false).maxAge(3600);
    }
}
