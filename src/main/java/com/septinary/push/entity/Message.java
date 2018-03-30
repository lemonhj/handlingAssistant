package com.septinary.push.entity;

/**
 * @author lin.tb lin.tb@septinary.com
 * @ClassName: Message
 * @Description: 推送消息实体
 * @date 16/3/25
 */
public class Message {
    private String title;
    private String content;
    private String logo = "icon.png";
    private String transmissionContent;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTransmissionContent() {
        return transmissionContent;
    }

    public void setTransmissionContent(String transmissionContent) {
        this.transmissionContent = transmissionContent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
