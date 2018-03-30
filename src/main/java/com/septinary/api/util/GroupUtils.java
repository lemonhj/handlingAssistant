package com.septinary.api.util;

import com.septinary.common.general.tool.PinYinHelper;

import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author lin.tb lin.tb@septinary.com
 * @ClassName: GroupUtils
 * @Description:
 * @date 16/8/20
 */
public class GroupUtils<K,V> {

    @SuppressWarnings("unused")
	public List<HashMap<K,V>> groupByPinyin(List<HashMap<K,V>> list){
        PinYinHelper pinyin = new PinYinHelper(HanyuPinyinCaseType.UPPERCASE, HanyuPinyinToneType.WITH_TONE_NUMBER);
        for (Iterator<HashMap<K,V>> iter = list.iterator(); iter.hasNext();) {
            HashMap<K,V> map = iter.next();

        }
        return null;
    }


}
