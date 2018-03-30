package com.septinary.common.web.tool;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 运行方式4：MyBatis entity生成器
 *
 * @Filename: com.septinary.common.tool.MyBatisGeneratorSeptinary.java of the project [com.septinary.common.web]
 *     @Type: MyBatisGeneratorSeptinary
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月16日下午2:38:21
 *
 *	源自：http://git.oschina.net/free/Mapper
 */
public class MyBatisGeneratorSeptinary {
	
	static public void main(String[] args) {
		
		System.out.println("MyBatisGeneratorSeptinary自动生成entity开始...");
		
		try {
			List<String> warnings = new ArrayList<String>();
			boolean overwrite = true;
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = null;
			//针对Eclipse下maven构建的项目可以通过此办法获取配置文件路径：
			String path = MyBatisGeneratorSeptinary.class.getResource("/").getPath().replaceAll("%20", " ").replaceAll("target/classes/$", "").concat("src/main/resources/");
			String file = "mybatis-generator-config.xml";
			config = cp.parseConfiguration(new java.io.File(path+file));
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(new ProgressCallback(){
				@Override
				public void introspectionStarted(int totalTasks) {
					// TODO Auto-generated method stub
					System.out.println("introspectionStarted:"+totalTasks);
				}
				@Override
				public void generationStarted(int totalTasks) {
					// TODO Auto-generated method stub
					System.out.println("generationStarted:"+totalTasks);
				}
				@Override
				public void saveStarted(int totalTasks) {
					// TODO Auto-generated method stub
					System.out.println("saveStarted:"+totalTasks);
				}
				@Override
				public void startTask(String taskName) {
					// TODO Auto-generated method stub
					System.out.println("startTask:"+taskName);
				}
				@Override
				public void done() {
					// TODO Auto-generated method stub
					System.out.println("done.");
				}
				@Override
				public void checkCancel() throws InterruptedException {
					// TODO Auto-generated method stub
					System.out.println("checkCancel.");
				}
			});
		} catch (Exception e) {
			System.out.println("MyBatisGeneratorSeptinary自动生成entity失败！");
			e.printStackTrace();
			return;
		}
		
		System.out.println("MyBatisGeneratorSeptinary自动生成entity完成。");
	}
}