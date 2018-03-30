package com.beigebigdata.bdCourt.ca.law.service;

import com.beigebigdata.bdCourt.ca.law.entity.LawCatalog;
import com.beigebigdata.bdCourt.ca.law.view.LawCatalogView;
import com.beigebigdata.bdCourt.ca.law.view.LawContentView;
import com.septinary.common.web.basic.service.IBaseService;

import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: LawCatalogService
 * @Description:
 * @date 16/11/18
 */
public interface LawCatalogService extends IBaseService<LawCatalog,Long> {

	/**
	 * 加载法律典籍的目录结构
	 * @param lawNo
	 * @return
	 */
	List<LawCatalogView> loadLawCatalog(Integer lawNo);

	/**
	 * 根据章节sn加载法律典籍的正文
	 * @param lawNo
	 * @param sn
	 * @return
	 */
	List<LawContentView> loadLawContent(Integer lawNo, String[] sn);
}
