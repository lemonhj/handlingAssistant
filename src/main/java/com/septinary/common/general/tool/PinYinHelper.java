package com.septinary.common.general.tool;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音助手
 * @Filename: com.septinary.common.general.tool.PinYinHelper.java of the project [com.septinary.common.general]
 *     @Type: PinYinHelper
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月18日下午3:56:53
 *
 *	依赖pinyin4j包。
 */
public class PinYinHelper {
    private HanyuPinyinOutputFormat format = null;
    public static enum Type {
        UPPERCASE,              //全部大写
        LOWERCASE,              //全部小写
        FIRSTUPPER              //首字母大写
    }

    public PinYinHelper(){
        format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    public PinYinHelper(HanyuPinyinCaseType caseType, HanyuPinyinToneType toneType){
        format = new HanyuPinyinOutputFormat();
        format.setCaseType(caseType);
        format.setToneType(toneType);
    }

    public String toPinYin(String str) throws BadHanyuPinyinOutputFormatCombination{
        return toPinYin(str, "", Type.UPPERCASE);
    }

    public String toPinYin(String str,String sperator) throws BadHanyuPinyinOutputFormatCombination{
        return toPinYin(str, sperator, Type.UPPERCASE);
    }

    /** 
     * 将汉字词语str转换成拼音，如果不是汉字或者没有对应的拼音，则不作转换 
     * 如： 明天 转换成 MINGTIAN 
     * @param str 
     * @param sperator 
     * @return 
     * @throws BadHanyuPinyinOutputFormatCombination 
     */
    public String toPinYin(String str, String sperator, Type type) throws BadHanyuPinyinOutputFormatCombination {
        if(str == null || str.trim().length()==0) {
            return "";
        }
        if(type == Type.UPPERCASE) {
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        } else {
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        }

        String py = "";
        String temp = "";
        String[] t;
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if((int)c <= 128) {
                py += c;
            } else {
                t = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if(t == null) {
                    py += c;
                } else {
                    temp = t[0];
                    if(type == Type.FIRSTUPPER)
                        temp = t[0].toUpperCase().charAt(0)+temp.substring(1);
                    py += temp+(i==str.length()-1?"":sperator);
                }
            }
        }
        return py.trim();
    }
}