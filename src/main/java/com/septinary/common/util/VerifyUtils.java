package com.septinary.common.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lin.tb lin.tb@septinary.com
 * @ClassName: VerifyUtils
 * @Description: 验证工具类
 * @date 16/8/23
 */
public class VerifyUtils {

    /**
     * 校验信息
     * @param targets 待验证的参数
     */
    public static void verifyReviseInfo(Object ... targets) {
        for (Object target :targets){
            if (target instanceof Object[]){
                org.springframework.util.Assert.notEmpty((Object[])target,"not allow empty");
            }else{
                org.springframework.util.Assert.notNull(target);
            }

        }
    }

    /**
     * 验证字符串是否为数字
     * @param str 待验证的字符串
     * @return
     */
    public static boolean isNumber(String str)
    {
        Pattern pattern=Pattern.compile("[0-9]*");
        Matcher match=pattern.matcher(str);
        return match.matches();
    }

    /**
     * 判断是否为手机号码
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^1[34578]\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


}
