package com.septinary.api.util;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.septinary.api.dto.ResulteDto;
import com.septinary.common.general.util.TimestampGsonAdapter;


/**
 * 
* @ClassName: ResponseUtils 
* @Description: 返回数据类型操作类 
* @author lin.tb lin.tb.septinary.com 
* @date 2015年4月27日 下午2:19:06 
*
 */
public class ResponseUtils {
	
	private static ResulteDto resulteDto = new ResulteDto();
	
	/**
	 * 
	* @Title: setResData 
	* @Description: 设置返回参数 
	* @param @param resultCode
	* @param @param resultMessage
	* @param @param resultData
	* @param @param res    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void setResData(Integer resultCode,String resultMessage,Object resultData,HttpServletResponse res){
		resulteDto.setCode(resultCode);
		resulteDto.setHint(resultMessage);
		Gson gson = new GsonBuilder().registerTypeAdapter(Timestamp.class, new TimestampGsonAdapter("yyyy-MM-dd HH:mm:ss")).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		res.setContentType("application/json; charset=utf-8");
		if(null != resultData){
			resulteDto.setData(resultData);
		}
		try {
			res.getWriter().write(gson.toJson(resulteDto));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
