package com.beigebigdata.bdCourt.ca.law.controller;

import com.beigebigdata.bdCourt.ca.law.entity.LawArt;
import com.beigebigdata.bdCourt.ca.law.entity.LawBase;
import com.beigebigdata.bdCourt.ca.law.service.LawArtService;
import com.beigebigdata.bdCourt.ca.law.service.LawBaseService;
import com.beigebigdata.bdCourt.ca.law.service.LawCatalogService;
import com.beigebigdata.bdCourt.ca.law.view.LawBookView;
import com.beigebigdata.bdCourt.ca.law.view.LawCatalogView;
import com.beigebigdata.bdCourt.ca.law.view.LawContentView;
import com.septinary.common.util.Assert;
import com.septinary.common.util.VerifyUtils;
import com.septinary.common.util.ViewJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: LawBaseController
 * @Description:法律基本信息访问接口
 * @date 16/10/21
 */
@Controller
@RequestMapping("v1.0/laws")
public class LawBaseController {
	
	@Autowired
	private LawBaseService lawBaseService;
	
	@Autowired
	private LawArtService lawArtService;

    @Autowired
    private LawCatalogService lawCatalogService;
	

	/**
	 * 加载相关法律典籍-根据案由编号
	 * @param causeNo
	 * @return
	 */
    @RequestMapping(value="/?byCauseNo", method= RequestMethod.GET)
    @ResponseBody
    public ViewJSON<List<LawBookView>> loadLawBooks(Integer causeNo) {
    	
    	Assert.NotNull(causeNo, "案由编号不能null！");
    	
    	return new ViewJSON<>("200", this.lawBaseService.queryLawBooksByCauseNo(causeNo));
    }

    /**
     * 加载法律典籍的目录结构下的条款-根据法律典籍编号、目录节点编号
     * @param lawNo
     * @return
     */
    @RequestMapping(value="/{lawNo}/loadLawArts", method= RequestMethod.GET)
    @ResponseBody
    public ViewJSON<List<LawArt>> loadLawArts(@PathVariable(value="lawNo")Integer lawNo, Integer catalogNo) {

    	Assert.NotNull(lawNo, "法律编号不能null！");
    	Assert.NotNull(catalogNo, "目录编号不能null！");
    	
    	return new ViewJSON<>("200", this.lawArtService.queryLawArtsByLawNo(lawNo,catalogNo));
    }


    /**
     * 加载法律典籍的目录结构
     * @param lawNo
     * @return
     */
    @RequestMapping(value="/{lawNo}/loadLawCatalogs", method= RequestMethod.GET)
    @ResponseBody
    public ViewJSON<List<LawCatalogView>> loadLawCatalog(@PathVariable(value="lawNo")Integer lawNo) {

        Assert.NotNull(lawNo, "法律编号不能null！");


        return new ViewJSON<>("200", lawCatalogService.loadLawCatalog(lawNo));
    }

    /**
     * 加载法律典籍的目录结构 返回树形结构
     * @param lawNo
     * @return
     */
    @RequestMapping(value="/{lawNo}/loadLawCatalogs/withTreeNode", method= RequestMethod.GET)
    @ResponseBody
    public ViewJSON<List<LawCatalogView>> loadLawCatalogWithTreeNode(@PathVariable(value="lawNo")Integer lawNo) {

        VerifyUtils.verifyReviseInfo(lawNo);

        List<LawCatalogView> lawCatalogs = lawCatalogService.loadLawCatalog(lawNo);

        List<LawCatalogView> nodeList = new ArrayList<>();
        for(LawCatalogView node1 : lawCatalogs){
            boolean mark = false;
            for(LawCatalogView node2 : lawCatalogs){
                if(node1.getpSn()!=null && node1.getpSn().equals(node2.getSn())){
                    mark = true;
                    if(node2.getChildren() == null)
                        node2.setChildren(new ArrayList<LawCatalogView>());
                    node2.getChildren().add(node1);
                    break;
                }
            }
            if(!mark){
                nodeList.add(node1);
            }
        }

        return new ViewJSON<>("200", nodeList);
    }


    /**
     * 获取制定法典的指定篇章内容
     * @param lawNo 法典编号
     * @param chapterSn 篇章sn
     * @return
     */
    @RequestMapping(value="/{lawNo}/LawContent/withTreeNode", method= RequestMethod.GET)
    @ResponseBody
    public ViewJSON<List<LawContentView>> loadLawCatalogWithTreeNode(@PathVariable(value="lawNo")Integer lawNo, String[] chapterSn) {

        VerifyUtils.verifyReviseInfo(lawNo, chapterSn);

        List<LawContentView> lawCatalogs = lawCatalogService.loadLawContent(lawNo,chapterSn);

        return new ViewJSON<>("200", lawCatalogs);
    }


    /**
     * 根据编号获取法典基本信息
     * @param lawNo
     * @return
     */
    @RequestMapping(value="/{lawNo}/info", method= RequestMethod.GET)
    @ResponseBody
    public ViewJSON<LawBase> fetchCodeInfo(@PathVariable(value="lawNo")Long lawNo) {

        VerifyUtils.verifyReviseInfo(lawNo);

        LawBase lawBase = lawBaseService.fetchCodeInfo(lawNo);

        return new ViewJSON<>("200", lawBase);
    }






}
