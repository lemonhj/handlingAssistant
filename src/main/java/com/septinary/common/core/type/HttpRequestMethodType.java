package com.septinary.common.core.type;

/**
 * HTTP请求方法类型
 * @Filename: com.septinary.common.core.type.HttpRequestMethodType.java of the project [com.septinary.common]
 *     @Type: HttpRequestMethodType
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午9:34:38
 *
 */
public enum HttpRequestMethodType {
	UNDEFINED,	//未定义的请求方式
	OPTIONS,	//检测能力,返回请求者和响应者之间可以使用的通信选项，主要用来检测服务器处理能力。
	GET,		//获取资源,获得以URL标识的文件内容或者程序执行结果。服务器根据文件名后缀判断服务内容，比如该URL是静态文本还是一个程序。
	HEAD,		//探测资源,除了不返回响应的消息本体以外，得到的是跟GET一样的信息。一般用来测试链接的有效性、可达性和近期修改。
	POST,		//提交资源,非幂等操作：把消息本体中的消息发送到一个URL或者其他类似的服务器端定义行为。通常用来提交一个HTML表单或者一些数据操作活动。
	PUT,		//上传资源,是幂等操作：把消息本体中的消息发送到一个URL，跟POST类似，但不常用。
	DELETE,		//删除资源,删除URL标识指定的资源。
	TRACE,		//跟踪路径,调用一个远程应用层请求消息回路。发出这个消息的用户终端除了收到原来的发出消息内容以外，还得到消息在Internet上的传送经过的路径。
	CONNECT		//请求代理,转换为透明TCP/IP隧道的连接请求。
}
