package com.beigebigdata.bdCourt.ca.law.service.impl;

import com.beigebigdata.bdCourt.ca.law.dao.LawCatalogDao;
import com.beigebigdata.bdCourt.ca.law.entity.LawCatalog;
import com.beigebigdata.bdCourt.ca.law.service.LawCatalogService;
import com.beigebigdata.bdCourt.ca.law.view.LawCatalogView;
import com.beigebigdata.bdCourt.ca.law.view.LawContentView;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: LawCatalogServiceImpl
 * @Description:
 * @date 16/11/17
 */
@Service("lawCatalogService")
public class LawCatalogServiceImpl extends BaseService<LawCatalog,Long> implements LawCatalogService {

    @Autowired
    private LawCatalogDao lawCatalogDao;

    @Override
    public IBaseDao<LawCatalog, Long> getBaseDao() {
        return lawCatalogDao;
    }

    @Override
    public List<LawCatalogView> loadLawCatalog(Integer lawNo) {
        return lawCatalogDao.loadLawCatalog(lawNo);
    }

    @Override
    public List<LawContentView> loadLawContent(Integer lawNo, String[] sn) {
        List<LawContentView> lawContents = lawCatalogDao.loadLawContent(lawNo);

        List<LawContentView> nodeList = new ArrayList<>();
        for(LawContentView node1 : lawContents){
            boolean mark = false;
            for(LawContentView node2 : lawContents){
                if(node1.getpSn()!=null && node1.getpSn().equals(node2.getSn())){
                    mark = true;
                    if(node2.getChildren() == null)
                        node2.setChildren(new ArrayList<LawContentView>());
                    node2.getChildren().add(node1);
                    break;
                }
            }
            if(!mark){
                nodeList.add(node1);
            }
        }
        List<String> snList = Arrays.asList(sn);


        List<LawContentView> contentViewList = new ArrayList<>();
        for (Iterator<LawContentView> iterator = nodeList.iterator();iterator.hasNext();){
            LawContentView lawContentView = iterator.next();
            if (snList.contains(lawContentView.getSn())) contentViewList.add(lawContentView);
        }

        return contentViewList;
    }
}
