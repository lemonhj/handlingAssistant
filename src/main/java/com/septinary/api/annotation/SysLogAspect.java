package com.septinary.api.annotation;

import com.google.gson.Gson;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;

/**
 *
 * @ClassName: SysLogAspect
 * @Description: controller 和 service 的log记录
 * @author lin.tb lin.tb@septinary.com 
 * @date 16/8/4
 */
@Aspect
@Component
public class SysLogAspect{

    private static final Logger logger = Logger.getLogger(SysLogAspect.class);

    @Pointcut("@annotation(com.septinary.api.annotation.SysExceptionLog)")
    public  void exceptionAspect() {
    }

    @Pointcut("@annotation(com.septinary.api.annotation.SysTraceLog)")
    public void traceLogAspect(){

    }


    /**
     * 前置拦截记录日志
     * @param joinPoint 切点
     */
    @Before("traceLogAspect()")
    public  void doBefore(JoinPoint joinPoint) {
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        Gson gson = new Gson();
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {
            for ( int i = 0; i < joinPoint.getArgs().length; i++) {
                if (joinPoint.getArgs()[i] instanceof HttpServletRequest) continue;
                params += gson.toJson(joinPoint.getArgs()[i]) + ";";
            }
        }
        try {
            logger.info("==================TraceLog开始=================");
            logger.info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            logger.info("请求参数:" + params);
            logger.info("方法描述:" + getMethodDescription(joinPoint));
            logger.info("==================TraceLog结束=================");
        }  catch (Exception e) {
            //记录本地异常日志
            logger.error("==================通知异常==================");
            logger.error("异常信息:{"+e.getMessage()+"}" );
        }
    }


    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(pointcut = "exceptionAspect()", throwing = "e")
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {

        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        Gson gson = new Gson();
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {
            for ( int i = 0; i < joinPoint.getArgs().length; i++) {
                params += gson.toJson(joinPoint.getArgs()[i]) + ";";
            }
        }
        try {

            logger.info("==================异常通知开始==================");
            logger.info("异常代码:" + e.getClass().getName());
            logger.info("异常信息:" + e.getMessage());
            logger.info("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            logger.info("方法描述:" + getServiceMthodDescription(joinPoint));
            logger.info("请求参数:" + params);

            logger.info("==================异常通知结束==================");
        }  catch (Exception ex) {
            //记录本地异常日志
            logger.error("==================异常通知异常==================");
            logger.error("异常信息:{"+ex.getMessage()+"}");
        }
         /*==========记录本地异常日志==========*/
        logger.error("异常方法:{"+joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName()+"}异常代码:{"+e.getClass().getName()+"}异常信息:{"+e.getMessage()+"}参数:{"+params+"}");

    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	public  static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SysExceptionLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的描述信息
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	public  static String getMethodDescription(JoinPoint joinPoint)  throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SysTraceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

}
