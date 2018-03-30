package com.septinary.api.exception;

import com.septinary.api.util.ResponseUtils;
import com.septinary.common.util.DateTimeUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lin.tb lin.tb@septinary.com
 * @ClassName: ApiExceptionHandler
 * @Description: 统一的异常处理类
 * @date 16/8/4
 */
@ControllerAdvice
public class ApiExceptionHandler {

    Logger logger =  Logger.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public void paramException(IllegalArgumentException ex, HttpServletResponse res) {
        logger.error(ex.getMessage(), ex);
        logger.info("************* ------ 异常信息已记录（" + DateTimeUtil.Now("yyyy-MM-dd HH:mm:ss")+ "） ------- ***********");
        ResponseUtils.setResData(1101, "非法的参数异常!", null, res);
    }
}
