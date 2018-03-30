package com.septinary.common.util;

import com.septinary.common.type.Result;
import com.septinary.common.type.Return;

/**
 * JSON视图类
 * @Filename: com.septinary.common.util.ViewJSON.java of the project [com.septinary.common]
 *     @Type: ViewJSON
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年1月27日下午1:00:13
 * 
 * @param <T>
 */
public class ViewJSON<T> extends View<T> {
	static private final String DEFAULT_OK_CODE = "200";
	static private final String DEFAULT_HINT_KEY_PREFIX = "api.reponse.code.";
	
	static public final String DEFAULT_HINT_KEY = DEFAULT_HINT_KEY_PREFIX + "{code}";
	
	public ViewJSON(String code, String hint, String memo, String info, T data) {
		super(code, hint, memo, info, data);
		// TODO Auto-generated constructor stub
	}
	
	//解决T为String时的构造
	public ViewJSON(String code, String hint, String memo, String info, T data, Class<T> classOfT) {
		super(code, hint, memo, info, data, classOfT);
		// TODO Auto-generated constructor stub
	}
	
	public ViewJSON(String code, IMessageText hint, String memo, String info, T data) {
		// TODO ... 读取国际化消息
		this(code, hint.getCode(), memo, info, data);
	}

	//解决T为String时的构造
	public ViewJSON(String code, IMessageText hint, String memo, String info, T data, Class<T> classOfT) {
		// TODO ... 读取国际化消息
		this(code, hint.getCode(), memo, info, data, classOfT);
	}
	
	public ViewJSON(String code, String hint, String memo, String info) {
		this(code, hint, memo, info, null);
		// TODO Auto-generated constructor stub
	}
	
	public ViewJSON(String code, IMessageText hint, String memo, String info) {
		// TODO ... 读取国际化消息
		this(code, hint.getCode(), memo, info);
	}

	public ViewJSON(String code, String hint, String memo) {
		super(code);
		// TODO Auto-generated constructor stub
		this.setHint(hint);
		this.setMemo(memo);
	}

	public ViewJSON(String code, IMessageText hint, String memo) {
		// TODO ... 读取国际化消息
		this(code, hint.getCode(), memo);
	}

	public ViewJSON(String code, String hint) {
		super(code);
		// TODO Auto-generated constructor stub
		this.setHint(hint);
	}

	public ViewJSON(String code, IMessageText hint) {
		// TODO ... 读取国际化消息
		this(code, hint.getCode());
	}
	
	public ViewJSON(String code) {
		super(code);
		// TODO Auto-generated constructor stub
	}
	
	public ViewJSON() {
		this(DEFAULT_OK_CODE);
		// TODO Auto-generated constructor stub
	}
	
	public ViewJSON(ViewJSON<T> view) {
		super(view);
		// TODO Auto-generated constructor stub
	}
	
	public ViewJSON(ViewJSON<T> view, String code) {
		super(view);
		this.setCode(code);
		// TODO Auto-generated constructor stub
	}
	
	public ViewJSON(View<T> view) {
		super(view);
		// TODO Auto-generated constructor stub
	}
	
	public ViewJSON(View<T> view, String code) {
		super(view);
		this.setCode(code);
		// TODO Auto-generated constructor stub
	}
	
	public ViewJSON(Return<T> ret) {
		super(ret);
		// TODO Auto-generated constructor stub
	}
	
	public ViewJSON(Return<T> ret, String code) {
		super(ret);
		this.setCode(code);
		// TODO Auto-generated constructor stub
	}
	
	public ViewJSON(Result result) {
		super(result);
		// TODO Auto-generated constructor stub
	}
	
	public ViewJSON(Result result, String code) {
		super(result);
		this.setCode(code);
		// TODO Auto-generated constructor stub
	}

	public ViewJSON(T data) {
		super(data);
		this.setCode(DEFAULT_OK_CODE);
		// TODO Auto-generated constructor stub
	}

	//解决T为String时的构造
	public ViewJSON(T data, Class<T> classOfT) {
		super(data, classOfT);
		this.setCode(DEFAULT_OK_CODE);
		// TODO Auto-generated constructor stub
	}
	
	public ViewJSON(String code, T data) {
		super(code);
		// TODO Auto-generated constructor stub
		this.setData(data);
	}

	//解决T为String时的构造
	public ViewJSON(String code, T data, Class<T> classOfT) {
		super(code);
		// TODO Auto-generated constructor stub
		this.setData(data);
	}
	
	public ViewJSON(String code, String hint, T data) {
		super(code);
		// TODO Auto-generated constructor stub
		this.setHint(hint);
		this.setData(data);
	}

	//解决T为String时的构造
	public ViewJSON(String code, String hint, T data, Class<T> classOfT) {
		super(code);
		// TODO Auto-generated constructor stub
		this.setHint(hint);
		this.setData(data);
	}
	
	public ViewJSON(String code, IMessageText hint, T data) {
		// TODO ... 读取国际化消息
		this(code, hint.getCode(), data);
	}

	//解决T为String时的构造
	public ViewJSON(String code, IMessageText hint, T data, Class<T> classOfT) {
		// TODO ... 读取国际化消息
		this(code, hint.getCode(), data, classOfT);
	}
	
	public ViewJSON(String code, String hint, String memo, T data) {
		super(code);
		// TODO Auto-generated constructor stub
		this.setHint(hint);
		this.setMemo(memo);
		this.setData(data);
	}

	//解决T为String时的构造
	public ViewJSON(String code, String hint, String memo, T data, Class<T> classOfT) {
		super(code);
		// TODO Auto-generated constructor stub
		this.setHint(hint);
		this.setMemo(memo);
		this.setData(data);
	}
	
	public ViewJSON(String code, IMessageText hint, String memo, T data) {
		// TODO ... 读取国际化消息
		this(code, hint.getCode(), memo, data);
	}

	//解决T为String时的构造
	public ViewJSON(String code, IMessageText hint, String memo, T data, Class<T> classOfT) {
		// TODO ... 读取国际化消息
		this(code, hint.getCode(), memo, data, classOfT);
	}

	@Override
	protected String okCode() {
		return DEFAULT_OK_CODE;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();

		//Gson gson = new GsonBuilder().registerTypeAdapter(Timestamp.class, new TimestampGsonAdapter("yyyy-MM-dd HH:mm:ss")).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		//return gson.toJson(this);
	}
}
