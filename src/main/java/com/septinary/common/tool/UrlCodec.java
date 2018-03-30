package com.septinary.common.tool;

import com.septinary.common.util.ICodecable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * URL组件的编解码
 * @Filename: com.septinary.common.tool.UrlCodec.java of the project [com.septinary.common]
 * @Type: UrlCodec
 * @Desc: TODO
 * @Author: macbook[weide<weide001@gmail.com>]
 * @Created: 2016-03-04 14:50:00
 */
public class UrlCodec implements ICodecable {
    //默认charset
    private String charset = null;

    public UrlCodec() {}
    public UrlCodec(String charset) {
        this.charset = charset;
    }

    @Override public String encode(String string, HashMap<Object, Object> option) throws UnsupportedEncodingException {
        if (null==string) return null;
        String charset = null==option ? this.charset : option.get("char-set").toString();
        return URLEncoder.encode(string,charset);
    }

    @Override public String decode(String string, HashMap<Object, Object> option) throws UnsupportedEncodingException {
        if (null==string) return null;
        String charset = null==option ? this.charset : option.get("char-set").toString();
        return URLDecoder.decode(string,charset);
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
