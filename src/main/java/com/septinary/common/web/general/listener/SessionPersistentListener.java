package com.septinary.common.web.general.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;

/**
 * 
 * @Filename: com.septinary.common.web.general.listener.SessionPersistentListener.java of the project [com.septinary.common.web]
 *     @Type: SessionPersistentListener
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月3日下午10:45:13
 *
 *	Session的主要数据被存储在服务器内存中，而服务器会为每个在线用户创建一个Session对象，当在线用户很多时，例
 *如同时有几万或是几十万在线的情况下，Session内存的开销将会十分巨大，会影响Web服务器性能。而Session的钝化机制
 *刚好可解决此问题。Session钝化机制的本质就在于把服务器中不经常使用的Session对象暂时序列化到系统文件系统或是数
 *据库系统中，当被使用时反序列化到内存中，整个过程由服务器自动完成。
 *
 *	实现：
 *要完成session持久化，存放在session里的对象必须要实现java.io.Serializable 接口。
 *Tomcat通过两个session管理类实现session持久化，StandardManager (默认)，PersistentManager
 *	StandardManager：不使用任何的Store,当Tomcat正常关闭，重启或Web应用重新加载时，它会将内存中的session序
 *列化到Tomcat目录下/work/Catalina/host_name/webappname/SESSIONS.ser文件中。当Tomcat重启或应用加载
 *完成后，tomcat会将文件中的session重新还原到内存中。
 *注：如果突然终止该服务器，则所有会话都将丧失，因为StandardManager没有机会实现存盘处理。
 *	PersistentManager: 通过使用Store,将内存中的session备份到文件或数据库中。当备份一个session对象时，该session
 *对象会被复制到存储器中，而原对象仍然留在内存中。因此如果服务器崩溃，就可以从存储器中获取活动的session对象。当session
 *被换出时，他会被移动到存储器中，因为当前活动的session对象超过了上限值，或者session对象闲置了过长时间。换出session
 *节省内存空间。
 *
 *	配置：
 *Tomcat默认是已经启用持久化配置，若要禁用持久化功能，则只需要在<Context>节点里配置
<Manager pathname="" />；
 *持久化配置可进行全局配置和针对某一站点进行配置。全局配置需要在conf文件夹context.xml的<Context>节点中配置<Manager>，
 *若要针对某一站点配置则需要在conf文件夹server.xml的<Host><Context>节点中添加<Manager>配置。
 *StandardManager：
<Manager className="org.apache.catalina.session.StandardManager" debug="0" maxInactiveInterval="-1" />
 *PersistentManager:
 *方式1：
<Manager className="org.apache.catalina.session.PersistentManager"
	saveOnRestart="true"
	maxActiveSession="-1"
	minIdleSwap="0"
	maxIdleSwap="30"
	maxIdleBackup="0"
> 
<Store className="org.apache.catalina.session.FileStore"
	checkInterval="60"
	directory="../session" />
</Manager>
 *
 *方式2：(和方式1的区别)
<Store className="org.apache.catalina.session.JDBCStore"
	checkInterval="60"
	debug="99"
    driverName="com.mysql.jdbc.Driver"
	connectionURL="jdbc:mysql://localhost:3306/x_septinary?user=root&amp;password=123456"
	sessionTable="base_session"
	sessionAppCol="sess_service"
	sessionIdCol="sess_code"
	sessionDataCol="sess_data"
	sessionLastAccessedCol="sess_visite"
	sessionMaxInactiveCol="sess_expire"
	sessionValidCol="sess_valid" />
 *参数说明：
 *className - tomcat允许用户自定义的类，然后打成jar包，放入$TOMCAT_HOME/lib中，就可以配置引用
 *saveOnRestart－(true/false)服务器关闭时，是否将所有的session保存到文件中：配置服务重启过程中对session的处理，若为true，则关闭前把有效的session保存，启动后重新载入
 *maxActiveSession－可处于活动状态的session数，default -1 不限制: 活动状态Session的最大数，为-1时则不限制，否则Session Manager将会把超出的Session对象转移到Session Store中
 *minIdleSwap - Session不活动的最短时间，超过该时间，Session Manager 可能会把该Session对象转移到Session Store中，单位为秒。
 *maxidleSwap - Session不活动的最长时间，超过该时间，Session Manager 将会把该Session对象转移到Session Store中，该Session将不在内存中。
 *maxidleBackup - Session不活动的最长时间，超过该时间，Session Manager 将会把该Session对象备份到Session Store中，但该Session对象依然存在内存中。
 *
 *checkInterval － 检查session是否过期的时间间隔，default 60s
 *directory－文件存储位置"../session"表示$TOMCAT_HOME/work/Catalina/{host name}/session/{jsessionid}.session
 *		   －文件存储位置"./session" 表示$TOMCAT_HOME/work/Catalina/{host name}/{web app name}/session/{jsessionid}.session
 *debug - Session管理器的跟踪级别
 *driverName - 需要MySQL驱动支持：cp /Users/macbook/Downloads/mysql-connector-java-5.1.34-bin.jar /Library/apache-tomcat-7.0.61/lib/
 *connectionURL - 数据库连接字符串
 *
 *方式3：TOMCAT的另一种SESSION持久化方法
 *继承StardSession,实现相应方法。当往SESSION里面保存属性的时候，调用setAttribute方 法，setAttribute方法的实现大概如下：
MemcacheClient mc =new MemcacheClient();
public void setAttribute(String name, Object value) {
	String key = name + this.getId();
	if(mc.keyExists(key)){
	    mc.replace(key, value);
	}else{
	    mc.set(key, value);
	}
}
*使用SESSIONID+属性名.通MemcachedClient.set()方法存入Memcached中.
*Context.xml配置文件
<Manager className="org.apache.catalina.session.MemcachedManager" debug=0
    saveOnRestart="true"
    maxActiveSession="-1"
    minIdleSwap="-1"
    maxIdleSwap="-1"
    maxIdleBackup="-1"
	serverlist="127.0.0.1:11211" >
...
 *
 *Tomcat session过期时间的设置，一般来说方法有四种：
 *1. 在tomcat——>conf——>servler.xml文件中定义：
<Context path="/test" docBase="/test"  
	defaultSessionTimeOut="3600"
	isWARExpanded="true" isWARValidated="false" isInvokerEnabled="true" isWorkDirPersistent="false" />
 *2. 在web.xml中定义：这个针对具体项目
<session-config>  
	<session-timeout>20</session-timeout>  
</session-config>
 *3. 在程序中定义：这个就针对具体页面了
session.setMaxInactiveInterval(30*60);
 *4.在conf/context.xml文件设置：这个是针对所有的项目了打开context.xml，在<Context>节点下添加如下<Manager>节点：
<Manager className="org.apache.catalina.session.PersistentManager"
	debug=0
    saveOnRestart="true"
    maxActiveSession="-1"
    minIdleSwap="-1"
    maxIdleSwap="-1"
    maxIdleBackup="-1"
    >
    <!-- 这里代表的是文件持久化.也可以自己实现Store -->
    <Store className="org.apache.catalina.session.FileStore" directory="../session" />
</Manager>
 *saveOnRestart:(true/false）配置服务重启工程中对session的处理，若为true，则关闭前把有效的session保存，启动后重新载入
 *maxActiveSession：活动状态Session的最大数，为-1时则不限制，否则Session Manager将会把超出的Session对象转移到Session Store中。
 *minIdleSwap：Session不活动的最短时间，超过该时间，Session Manager 可能会把该Session对象转移到Session Store中，单位为秒。
 *maxidleSwap：Session不活动的最长时间，超过该时间，Session Manager 将会把该Session对象转移到Session Store中，该Session将不在内存中。
 *maxidleBackup: Session不活动的最长时间，超过该时间，Session Manager 将会把该Session对象备份到Session Store中，但该Session对象依然存在内存中。
 *<Store>指定实现持久化的类和Session存放的文件位置，如该例子中指定的类是：org.apache.catalina.session.FileStore，而Session对象存放的目录则是tomcat根目录下的 session文件夹（当然自己创建）
 *在第四种配置中，配置完后可以写一个简单的jsp页面，在页面上显示本次用户访问的Session ID,然后重起tomcat，再刷新该页面，可以看到该Session Id不变，而在/session目录下自动生成一个以session id为名，以“session”为扩展名的文件。该Session的持久化配置成功。
 */
@WebListener
public class SessionPersistentListener extends CommonListenerSession {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		super.sessionCreated(se);
		
		logger.verbose("SESSION:" + se.getSession().getId() + " has been created。");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		// super.sessionDestroyed(se);
		
		logger.verbose("SESSION:" + se.getSession().getId() + " has being destroying ...");
		
		super.sessionDestroyed(se);
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		super.attributeAdded(event);
		
		logger.verbose("SESSION:"+ event.getSession().getId() + " has been added \"" + event.getName() + "\"=" + event.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		// super.attributeRemoved(event);
		
		logger.verbose("SESSION:"+ event.getSession().getId() + " has being removing \"" + event.getName() + "\"");
		
		super.attributeRemoved(event);
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		super.attributeReplaced(event);
		
		logger.verbose("SESSION:"+ event.getSession().getId() + " has been saved \"" + event.getName() + "\"=" + event.getValue());
	}

}
