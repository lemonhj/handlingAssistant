package com.beigebigdata.bdCourt.ca.law.view;

import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: LawContentView
 * @Description:
 * @date 16/11/18
 */
public class LawContentView {
    /**
     * 法律法规编号
     */
    private Integer lawNo;

    /**
     * 最后一层子节点  1.00~1000，上一层字节点 1001~1100，上上层子节点 1101~1200，父节点1201~1300,与art_no关联
     */
    private String sn;

    /**
     * 章、节..的名称
     */
    private String contName;

    /**
     * 父序号(上一级序号)
     */
    private String pSn;

    private String artCont;

    private String artNoDesc;

    private Integer directoryType;

    public Integer getDirectoryType() {
        return directoryType;
    }

    public void setDirectoryType(Integer directoryType) {
        this.directoryType = directoryType;
    }

    public Integer getLawNo() {
        return lawNo;
    }

    public void setLawNo(Integer lawNo) {
        this.lawNo = lawNo;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getContName() {
        return contName;
    }

    public void setContName(String contName) {
        this.contName = contName;
    }

    public String getpSn() {
        return pSn;
    }

    public void setpSn(String pSn) {
        this.pSn = pSn;
    }

    public String getArtCont() {
        return artCont;
    }

    public void setArtCont(String artCont) {
        this.artCont = artCont;
    }

    public String getArtNoDesc() {
        return artNoDesc;
    }

    public void setArtNoDesc(String artNoDesc) {
        this.artNoDesc = artNoDesc;
    }

    private List<LawContentView> children;

    public List<LawContentView> getChildren() {
        return children;
    }

    public void setChildren(List<LawContentView> children) {
        this.children = children;
    }
}
