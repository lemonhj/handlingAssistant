package com.septinary.common.util;

import java.util.HashMap;

/**
 * 对端配置
 * @Filename: com.septinary.common.util.RemoteConfig.java of the project [com.septinary.common]
 * @Type: RemoteConfig
 * @Desc: TODO
 * @Author: macbook[weide<weide001@gmail.com>]
 * @Created: 2016-03-01 18:15:00
 */
abstract public class RemoteConfig {
    protected String protocol = "";   //协议名称
    protected String host = "";       //主机地址
    protected Integer port = 0;       //主机端口
    protected String appname = "";    //应用名称
    protected String version = "";    //应用版本
    protected String prefix = "";     //路由前缀
    protected String suffix = "";     //路由后缀
    protected String username = "";   //账户名称
    protected String password = "";   //账户密码
    protected HashMap<Object, Object> options = null;

    public RemoteConfig() {
    }

    public RemoteConfig(String protocol, String host, Integer port, String appname) {
        this.setProtocol(protocol);
        this.setHost(host);
        this.setPort(port);
        this.setAppname(appname);
    }

    public RemoteConfig(String protocol, String host, Integer port, String appname,
        String version) {
        this.setProtocol(protocol);
        this.setHost(host);
        this.setPort(port);
        this.setAppname(appname);
        this.setVersion(version);
    }

    public RemoteConfig(String protocol, String host, Integer port, String appname, String version, String prefix, String suffix) {
        this.setProtocol(protocol);
        this.setHost(host);
        this.setPort(port);
        this.setAppname(appname);
        this.setVersion(version);
        this.setPrefix(prefix);
        this.setSuffix(suffix);
    }

    public RemoteConfig(String protocol, String host, Integer port, String appname, String version, String prefix, String suffix, String username, String password) {
        this.setProtocol(protocol);
        this.setHost(host);
        this.setPort(port);
        this.setAppname(appname);
        this.setVersion(version);
        this.setPrefix(prefix);
        this.setSuffix(suffix);
        this.setUsername(username);
        this.setPassword(password);
    }

    public RemoteConfig(String protocol, String host, Integer port, String appname, String version, String prefix, String suffix, String username, String password, HashMap<Object, Object> options) {
        this.setProtocol(protocol);
        this.setHost(host);
        this.setPort(port);
        this.setAppname(appname);
        this.setVersion(version);
        this.setPrefix(prefix);
        this.setSuffix(suffix);
        this.setUsername(username);
        this.setPassword(password);
        this.setOptions(options);
    }

    //连接字符串制作
    abstract public String connectstring();

    //请求字符串制作
    abstract public String requeststring(String target, String action, HashMap<Object, Object> params, String selector);

    @Override
    public String toString() {
        return this.connectstring();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<Object, Object> getOptions() {
        return options;
    }

    public void setOptions(HashMap<Object, Object> options) {
        this.options = options;
    }
}
