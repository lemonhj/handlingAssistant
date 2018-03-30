package com.septinary.common.type;

/**
 * 处理结果
 * @Filename: com.septinary.common.type.Result.java of the project [com.septinary.common]
 *     @Type: Result
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月4日 下午12:01:15
 */
public class Result {
	static private final String DEFAULT_OK_CODE = "00";

	//返回代码
	private String code = DEFAULT_OK_CODE;
	
	//返回信息
	private String info = null;
	
	//备注信息
	private String memo = null;
	
	public Result(String code, String memo, String info) {
		this.setCode(code);
		this.setInfo(info);
		this.setMemo(memo);
	}
	
	public Result(String code, String memo) {
		this(code, memo, null);
	}
	
	public Result(String code) {
		this(code, null);
	}
	
	public Result() {}
	
	public Result(Result result) {
		if(null==result) return;
		this.setCode(result.getCode());
		this.setInfo(result.getInfo());
		this.setMemo(result.getMemo());
	}

	public String getCode() {
		return code;
	}

	public Result setCode(String code) {
		if(null!=code) this.code = code;
		return this;
	}

	public String getInfo() {
		return info;
	}

	public Result setInfo(String info) {
		this.info = info;
		return this;
	}

	public String getMemo() {
		return memo;
	}

	public Result setMemo(String memo) {
		this.memo = memo;
		return this;
	}
	
	/**
	 * 判断是否成功代码？
	 * @param code
	 * @return
	 */
	public boolean ok(String code) {
		if(null==code) code = this.okCode();
		return code.equals(this.getCode());
	}
	public boolean ok() {
		return this.ok(null);
	}
	protected String okCode() {
		return DEFAULT_OK_CODE;
	}
}
