package com.septinary.common.type;

/**
 * 返回对象
 * @Filename: com.septinary.common.type.Return.java of the project [com.septinary.common]
 *     @Type: Return
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月22日下午3:23:51
 *
 */
public class Return<T> extends Result {
	
	//返回数据
	private T data = null;

	public Return(String code, String memo, String info, T data) {
		super(code, memo, info);
		this.setData(data);
	}

	//解决T为String时的构造
	public Return(String code, String memo, String info, T data, Class<T> classOfT) {
		super(code, memo, info);
		this.setData(data);
	}
	
	public Return(String code, String memo, String info) {
		this(code, memo, info, null);
	}

	public Return(String code, String memo) {
		this(code, memo, null, null, null);
	}
	
	public Return(String code) {
		this(code, null, null, null, null);
	}
	
	public Return() {}

	public Return(Result result) {
		super(result);
	}
	
	public Return(Return<T> ret) {
		this(ret.getCode(), ret.getMemo(), ret.getInfo(), ret.getData());
	}

	public Return(T data) {
		this(null, null, null, data);
	}

	//解决T为String时的构造
	public Return(T data, Class<T> classOfT) {
		this(null, null, null, data, classOfT);
	}
	
	public Return(String code, T data) {
		this(code, null, null, data);
	}

	//解决T为String时的构造
	public Return(String code, T data, Class<T> classOfT) {
		this(code, null, null, data, classOfT);
	}
	
	public Return(String code, String memo, T data) {
		this(code, memo, null, data);
	}

	//解决T为String时的构造
	public Return(String code, String memo, T data, Class<T> classOfT) {
		this(code, memo, null, data, classOfT);
	}

	public T getData() {
		return data;
	}

	public Return<T> setData(T data) {
		this.data = data;
		return this;
	}
}
