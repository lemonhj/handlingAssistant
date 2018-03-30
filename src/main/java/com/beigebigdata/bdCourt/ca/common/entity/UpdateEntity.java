package com.beigebigdata.bdCourt.ca.common.entity;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: UpdateEntity
 * @Description:增量更新请求的实体类
 * @date 17/6/23
 */
public class UpdateEntity {

    //请求数量
    private int count;

    //请求页数
    private int page;

    //请求时间点
    private long since;

    //请求截止时间
    private long max;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getSince() {
        return since;
    }

    public void setSince(long since) {
        this.since = since;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }
}
