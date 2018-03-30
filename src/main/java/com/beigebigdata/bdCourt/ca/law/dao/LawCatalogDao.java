package com.beigebigdata.bdCourt.ca.law.dao;

import com.beigebigdata.bdCourt.ca.law.entity.LawCatalog;
import com.beigebigdata.bdCourt.ca.law.view.LawCatalogView;
import com.beigebigdata.bdCourt.ca.law.view.LawContentView;
import com.septinary.common.web.basic.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: LawCatalogDao
 * @Description:
 * @date 16/11/17
 */
public interface LawCatalogDao  extends IBaseDao<LawCatalog,Long> {

    /**
     *
     * @param lawNo
     * @return
     */
    List<LawCatalogView> loadLawCatalog(@Param("lawNo") Integer lawNo);

    List<LawContentView> loadLawContent(@Param("lawNo") Integer lawNo);
}
