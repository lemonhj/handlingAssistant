package com.septinary.common.type.basic;

/**
 * 编号
 * @Filename: com.septinary.common.type.CODE.java of the project [com.septinary.common]
 *     @Type: CODE
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月22日下午11:34:47
 * 
 * @param <PK>
 * @param <SK>
 */
abstract public class CODE<PK, SK> extends ID<PK> implements ICodeable<PK, SK> {

	private SK code;
	
	public CODE(PK id, SK code) {
		super(id);
		this.code = code;
	}
	
	public CODE(PK id) {
		this(id, null);
	}
	
	public CODE() {
		this(null, null);
	}

	public SK getCode() {
		return code;
	}

	public void setCode(SK code) {
		this.code = code;
	}
}
