package com.septinary.common.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * 编码/解码接口
 * @Filename: com.septinary.common.util.ICodeable.java of the project [com.septinary.common]
 * @Type: ICodeable
 * @Desc: TODO
 * @Author: macbook[weide<weide001@gmail.com>]
 * @Created: 2016-03-02 10:48:00
 */
public interface ICodecable {
	
  public String encode(String string, HashMap<Object, Object> option) throws UnsupportedEncodingException;
  
  public String decode(String string, HashMap<Object, Object> option) throws UnsupportedEncodingException;
}
