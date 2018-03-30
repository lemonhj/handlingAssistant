package com.septinary.push.entity;

import java.util.List;

/**
 * @author lin.tb lin.tb@septinary.com
 * @ClassName: PushCondition
 * @Description:
 * @date 16/9/14
 */
public class PushCondition {
    /**
     * 推送终端类型
     */
    private List<String> phoneType;

    /**
     * 推送范围
     */
    private List<String> region;

    /**
     * 推送标签
     */
    private List<String> tag;

    public List<String> getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(List<String> phoneType) {
        this.phoneType = phoneType;
    }

    public List<String> getRegion() {
        return region;
    }

    public void setRegion(List<String> region) {
        this.region = region;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }
}
