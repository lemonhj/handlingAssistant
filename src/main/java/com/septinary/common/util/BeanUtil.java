package com.septinary.common.util;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtilsBean;

import com.alibaba.druid.util.StringUtils;

/**
 * Bean操作
 * @Filename: com.septinary.common.util.BeanUtil.java of the project [com.septinary.common]
 *     @Type: BeanUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016-5-27 12:36:28
 *
 */
public abstract class BeanUtil {

    /**
     * 将javabean转为map类型，然后返回一个map类型的值
     * @param obj 需要转换的bean
     * @return
     */
    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> params = new HashMap<String, Object>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                Object value = propertyUtilsBean.getNestedProperty(obj, name);
                if (!StringUtils.equals(name, "class") && value != null) {
                    params.put(name, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }
    
    /**
     * 对象属性拷贝
     * @param source
     * @param target
     * @param editable
     * @param ignoreProperties	需要忽略的字段名称列表
     * @throws Exception
     */
    public static void Copy(Object source, Object target, Class<?> editable, String... ignoreProperties) throws Exception {

		Assert.NotNull(source, "Source must not be null");
		Assert.NotNull(target, "Target must not be null");

	}
    
    /**
     * 对象属性拷贝
     * @param source
     * @param target
     * @param ignoreProperties	需要忽略的字段名称列表
     * @throws Exception
     */
    public static void Copy(Object source, Object target, String... ignoreProperties) throws Exception {

		Copy(source, target, null, ignoreProperties);

	}

    /**
     * 对象属性拷贝
     * @param source
     * @param target
     * @throws Exception
     */
    public static void Copy(Object source, Object target) throws Exception {

		Copy(source, target, null, new String[]{});

	}

    /**
     * 对象属性拷贝
     * @param source
     * @param target
     * @param fromToPairs	字段对应关系，如果不设置对应关系，则默认拷贝（即字段名称对应关系拷贝）
     * @throws Exception
     */
    public static void Copy(Object source, Object target, HashMap<String,String> fromToPairs) throws Exception {

		Copy(source, target, null, new String[]{});

	}
    
    /**
     * 清空对象属性
     * @param object
     */
    public static void Clear(Object object) {
    	
    }
    
    /**
     * 清空对象属性
     * @param object
     * @param ignoreProperties	忽略的属性列表
     */
    public static void Clear(Object object, String... ignoreProperties) {
    	
    }
}
