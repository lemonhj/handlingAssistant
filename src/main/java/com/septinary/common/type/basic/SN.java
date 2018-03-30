package com.septinary.common.type.basic;

/**
 * SN
 * @Filename: com.septinary.common.type.SN.java of the project [com.septinary.common]
 *     @Type: SN
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月22日下午4:11:19
 * 
 * @param <PK>
 * @param <SK>
 */
abstract public class SN<PK, SK> extends ID<PK> implements ISnable<PK, SK> {

	private SK sn;
	
	public SN(PK id, SK sn) {
		super(id);
		this.sn = sn;
	}
	
	public SN(PK id) {
		this(id, null);
	}
	
	public SN() {
		this(null, null);
	}

	public SK getSn() {
		return sn;
	}

	public void setSn(SK sn) {
		this.sn = sn;
	}
}
