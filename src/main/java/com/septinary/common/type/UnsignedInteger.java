package com.septinary.common.type;

import java.math.BigInteger;

import com.septinary.common.core.exception.NumericOutOfBoundaryException;

/**
 * 无符号整型数
 * @Filename: com.septinary.common.type.UnsignedInteger.java of the project [com.septinary.common]
 *     @Type: UnsignedInteger
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月21日下午6:01:52
 *
 */
@SuppressWarnings("serial")
public class UnsignedInteger extends Number implements Comparable<UnsignedInteger> {
	
	static private final long MIN_VALUE = 0L;
	static private final long MAX_VALUE = (long)(Integer.MAX_VALUE)-(long)(Integer.MIN_VALUE);
	
	private long value = 0L;
	
	public UnsignedInteger() {}
	
	public UnsignedInteger(UnsignedInteger v) {
		this.value = v.value;
	}
	
	public UnsignedInteger(boolean v) {
		this.setValue(v ? 1L : 0L);
	}
	
	public UnsignedInteger(byte v) {
		try {
			this.setValue(v);
		} catch (NumericOutOfBoundaryException noobe) {
		}
	}
	
	public UnsignedInteger(char v) {
		this.setValue(v);
	}
	
	public UnsignedInteger(int v) {
		try {
			this.setValue(v);
		} catch (NumericOutOfBoundaryException noobe) {
		}
	}
	
	public UnsignedInteger(long v) {
		try {
			this.setValue(v);
		} catch (NumericOutOfBoundaryException noobe) {
		}
	}
	
	public UnsignedInteger(float v) {
		try {
			this.setValue((long)v);
		} catch (NumericOutOfBoundaryException noobe) {
		}
	}
	
	public UnsignedInteger(double v) {
		try {
			this.setValue((long)v);
		} catch (NumericOutOfBoundaryException noobe) {
		}
	}
	
	public UnsignedInteger(BigInteger v) {
		try {
			this.setValue(v.longValue());
		} catch (NumericOutOfBoundaryException noobe) {
		}
	}
	
	private void setValue(long value) {
		if(value>UnsignedInteger.MAX_VALUE || value<UnsignedInteger.MIN_VALUE) throw new NumericOutOfBoundaryException();
		this.value = value;
	}

	@Override
	public int compareTo(UnsignedInteger o) {
		// TODO Auto-generated method stub
		// return 0;
		
		return (int)(this.value - o.value);
	}
	
	public int compareTo(Integer o) {
		return (int)(this.value - o);
	}
	
	public boolean boolValue() {
		return 0==this.value ? true : false;
	}
	
	public byte byteValue() {
		return Byte.MAX_VALUE < this.value ? Byte.MAX_VALUE : (byte)(this.value);
	}
	
	public char charValue() {
		return (char)(this.value);
	}

	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		// return 0;
		
		return Integer.MAX_VALUE < this.value ? Integer.MAX_VALUE : (int)(this.value);
	}

	@Override
	public long longValue() {
		// TODO Auto-generated method stub
		// return 0;
		
		return this.value;
	}

	@Override
	public float floatValue() {
		// TODO Auto-generated method stub
		// return 0;
		
		return (float)(this.value);
	}

	@Override
	public double doubleValue() {
		// TODO Auto-generated method stub
		// return 0;
		
		return (double)(this.value);
	}
	
	public BigInteger bigIntValue() {
		return BigInteger.valueOf(this.value);
	}

}
