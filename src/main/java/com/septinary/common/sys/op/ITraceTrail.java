package com.septinary.common.sys.op;

import java.util.Date;

/**
 * 跟踪轨迹接口
 * @Filename: com.septinary.common.sys.op.ITrace.java of the project [com.septinary.common]
 *     @Type: ITrace
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月8日 下午7:40:22
 */
public interface ITraceTrail<SK> {

    public SK getId();
    public void setId(SK id);
    
    public String getUserClassname();
    public void setUserClassname(String userClassname);
    
    public String getUserId();
    public void setUserId(String userId);
    
    public String getUserName();
    public void setUserName(String userName);
    
    public Date getActtime();
    public void setActtime(Date acttime);
    
    public String getObjectClassname();
    public void setObjectClassname(String objectClassname);
    
    public String getObjectId();
    public void setObjectId(String objectId);
    
    public String getObjectName();
    public void setObjectName(String objectName);
    
    public String getActid();
    public void setActid(String actid);
    
    public String getActtag();
    public void setActtag(String acttag);
    
    public String getActsubject();
    public void setActsubject(String actsubject);
    
    public String getSessionid();
    public void setSessionid(String sessionid);
    
    public String getToken();
    public void setToken(String token);
    
    public Integer getTerminal();
    public void setTerminal(Integer terminal);
    
    public Integer getComefrom();
    public void setComefrom(Integer comefrom);
    
    public Integer getNetwork();
    public void setNetwork(Integer network);
    
    public Integer getOperators();
    public void setOperators(Integer operators);
    
    public String getHost();
    public void setHost(String host);
    
    public String getIp();
    public void setIp(String ip);
    
    public String getDevice();
    public void setDevice(String device);
    
    public String getImei();
    public void setImei(String imei);
    
    public String getOs();
    public void setOs(String os);
    
    public String getClient();
    public void setClient(String client);
    
    public String getClientid();
    public void setClientid(String clientid);
    
    public String getUseragent();
    public void setUseragent(String useragent);
    
    public String getAppname();
    public void setAppname(String appname);
    
    public String getVersion();
    public void setVersion(String version);
    
    public Float getX();
    public void setX(Float x);

    public Float getY();
    public void setY(Float y);

    public Float getZ();
    public void setZ(Float z);

    public String getArea();
    public void setArea(String area);

    public String getAddress();
    public void setAddress(String address);

    public String getActextra();
    public void setActextra(String actextra);
}
