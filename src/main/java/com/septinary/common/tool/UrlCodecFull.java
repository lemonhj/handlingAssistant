package com.septinary.common.tool;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * URL全路径的编解码
 * @Filename: com.septinary.common.tool.URLCodecFull.java of the project [com.septinary.common]
 * @Type: URLCodecFull
 * @Desc: TODO
 * @Author: macbook[weide<weide001@gmail.com>]
 * @Created: 2016-03-04 16:22:00
 */
public class UrlCodecFull extends UrlCodec {
    //标准：RFC3986
    static private String unescape = "!#$&'()*+,/:;=?@-._~"; // + "0-9a-zA-Z"

    public UrlCodecFull() {}
    public UrlCodecFull(String charset) {
        super(charset);
    }

    @Override public String encode(String url, HashMap<Object, Object> option) throws UnsupportedEncodingException {
        if (null==url) return null;

        String uri = "";
        for (int i=0; i<url.length(); i++) {
            Character c = url.charAt(i);
            if( unescape.contains(String.valueOf(c)) || ('0'<=c&&'9'>=c) || ('a'<=c&&'z'>=c) || ('A'<=c&&'Z'>=c) ) {
                uri += String.valueOf(url.charAt(i));
            } else {
                uri += super.encode(String.valueOf(url.charAt(i)),option);
            }
        }
        return uri;
    }

    @Override public String decode(String url, HashMap<Object, Object> option) throws UnsupportedEncodingException {
        if (null==url) return null;

        String uri = super.decode(url,option);

        return uri;
    }
}
