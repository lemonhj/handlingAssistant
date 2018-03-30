package com.septinary.common.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigInteger;

import com.septinary.common.general.util.GsonUtil;
import com.septinary.common.manager.ConfiggerManager;
import com.septinary.common.type.ISerializable;
import com.septinary.common.type.Value;
import com.septinary.common.util.ArrayUtil;
import com.septinary.common.util.StringUtil;

/**
 * 配置读取适配器
 * @Filename: com.septinary.common.core.util.ConfigReaderable.java of the project [com.septinary.common]
 * @Type: ConfigGetter
 * @Desc: TODO
 * @Author: macbook[weide<weide001@gmail.com>]
 * @Created: 2016-03-03 14:15:00
 */
abstract public class ConfigReaderable {
    private IConfigGetter configger = null;

    public ConfigReaderable() {}

    //懒惰加载
    protected IConfigGetter getConfigger() {
    	if(null==this.configger) {
            System.out.println("Getting configReader from sigleton:ConfiggerManager ...");
            this.configger = ConfiggerManager.Instance().getReader();
    	}
    	return this.configger;
    }
    
    //静态配置读取器
    static private ConfigReaderable reader = new ConfigReaderable(){};
    static public ConfigReaderable GetReader() {
    	return reader;
    }
    
    //配置读取调用适配
    public String get(String key) {
    	Value<Object> item = this.getConfigger().get(key);
    	if( null==item || byte[].class.equals(item.getValue().getClass()) ) return null;
    	//暂时直接toString()，应该根据配置类型相应转换
        return item.getValue().toString();
    }
    public byte[] getBytes(String key, byte[] defaultvalue){
    	Value<Object> item = this.getConfigger().get(key);
    	if( null==item || !byte[].class.equals(item.getValue().getClass()) ) return defaultvalue;
    	return (byte[])item.getValue();
    }
    public byte[] getBytes(String key) {
    	return getBytes(key, null);
    }

    //友好读取
    public Boolean getBoolean(String key, Boolean defaultvalue){
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	return "true".equalsIgnoreCase(value) ? true : ("false".equalsIgnoreCase(value)?false:defaultvalue);
    }
    public Boolean getBoolean(String key){
    	return this.getBoolean(key,null);
    }
    public boolean getBool(String key, boolean defaultvalue){
    	Boolean bool = this.getBoolean(key);
    	return null==bool ? defaultvalue : bool;
    }
    public boolean getBool(String key){
    	return getBool(key, false);
    }

    public Boolean[] getBooleans(String key, String separator, Boolean[] defaultvalue){
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	String[] values = value.split(separator);
    	Boolean[] bools = new Boolean[values.length];
    	int index = 0;
    	for(String item: values) {
    		try {
    			Boolean b = Boolean.valueOf(item.toLowerCase());
        		bools[index] = b;
        	} catch (Exception e) {
        		e.printStackTrace();
        		bools[index] = ArrayUtil.get(defaultvalue, index, null); //发生异常时，使用默认元素值
        	}
    		index ++;
		}
    	return bools;
    }
    public Boolean[] getBooleans(String key){
    	return this.getBooleans(key, ",", null);
    }
    public boolean[] getBools(String key, String separator, boolean[] defaultvalue){
    	Boolean[] bools = getBooleans(key,separator,null);
    	if(null==bools) return defaultvalue;
    	boolean[] bs = new boolean[bools.length];
    	int index = 0;
    	for(Boolean b: bools) {
    		bs[index] = null==b ? ArrayUtil.get(defaultvalue, index, false) : b;
    		index ++;
    	}
    	return bs;
    }
    public boolean[] getBools(String key){
    	return getBools(key, ",", null);
    }

    public Byte[] getByteObjects(String key, Byte[] defaultvalue){
    	byte[] bytes = getBytes(key);
    	if( null==bytes ) return defaultvalue;
    	Byte[] bs = new Byte[bytes.length];
    	int index = 0;
    	for(byte b: bytes) {
    		bs[index++] = b;
    	}
    	return bs;
    }
    public Byte[] getByteObjects(String key) {
    	return getByteObjects(key, null);
    }
    
    public Character getCharacter(String key, Character defaultvalue){
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	return value.toCharArray()[0];
    }
    public Character getCharacter(String key){
    	return this.getCharacter(key,null);
    }
    public char getChar(String key, char defaultvalue){
    	Character ch = getCharacter(key);
    	return null==ch ? defaultvalue : ch;
    }
    public char getChar(String key){
    	return getChar(key, (char)0);
    }
    
    public Character[] getCharacters(String key, Character[] defaultvalue){
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	Character[] chars = new Character[value.length()];
    	int index = 0;
    	for(Character c: value.toCharArray()) {
    		chars[index++] = c;
    	}
    	return chars;
    }
    public Character[] getCharacters(String key){
    	return this.getCharacters(key,null);
    }
    public char[] getChars(String key, char[] defaultvalue){
    	Character[] chs = getCharacters(key);
    	if(null==chs) return defaultvalue;
    	char[] chars = new char[chs.length];
    	int index = 0;
    	for(Character c: chs) {
    		chars[index] = null==c ? ArrayUtil.get(defaultvalue, index, (char)0) : c;
    		index ++;
    	}
    	return chars;
    }
    public char[] getChars(String key){
    	return getChars(key,null);
    }
    
    public Short getShortInteger(String key, Short defaultvalue){
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	Short s = defaultvalue;
    	try {
    		s = Short.valueOf(value);
    	} catch (NumberFormatException nfe) {
    		nfe.printStackTrace();
    	}
    	return s;
    }
    public Short getShortInteger(String key){
    	return this.getShortInteger(key,null);
    }
    public short getShort(String key, short defaultvalue){
    	Short sh = getShortInteger(key);
    	return null==sh ? defaultvalue : sh;
    }
    public short getShort(String key){
    	return getShort(key, (short)0);
    }
    
    public Short[] getShortIntegers(String key, String separator, Short[] defaultvalue){
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	String[] values = value.split(separator);
    	Short[] shorts = new Short[]{};
    	int index = 0;
    	for(String item: values) {
    		try {
        		Short s = Short.valueOf(item);
        		shorts[index] = s;
        	} catch (NumberFormatException nfe) {
        		nfe.printStackTrace();
        		shorts[index] = ArrayUtil.get(defaultvalue, index, null); //发生异常时，使用默认元素值
        	}
    		index ++;
		}
    	return shorts;
    }
    public Short[] getShortIntegers(String key){
    	return this.getShortIntegers(key, ",", null);
    }
    public short[] getShorts(String key, String separator, short[] defaultvalue){
    	Short[] shorts = getShortIntegers(key,separator,null);
    	if(null==shorts) return defaultvalue;
    	short[] shs = new short[shorts.length];
    	int index = 0;
    	for(Short s: shorts) {
    		shs[index] = null==s ? ArrayUtil.get(defaultvalue, index, (short)0) : s;
    		index ++;
    	}
    	return shs;
    }
    public short[] getShorts(String key){
    	return getShorts(key, ",", null);
    }
    
    public Integer getInteger(String key, Integer defaultvalue){
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	Integer i = defaultvalue;
    	try {
    		i = Integer.valueOf(value);
    	} catch (NumberFormatException nfe) {
    		nfe.printStackTrace();
    	}
    	return i;
    }
    public Integer getInteger(String key){
    	return this.getInteger(key, null);
    }
    public int getInt(String key, int defaultvalue){
    	Integer i = getInteger(key);
    	return null==i ? defaultvalue : i;
    }
    public int getInt(String key){
    	return getInt(key, 0);
    }
    
    public Integer[] getIntegers(String key, String separator, Integer[] defaultvalue){
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	String[] values = value.split(separator);
    	Integer[] ints = new Integer[]{};
    	int index = 0;
    	for(String item: values) {
    		try {
    			Integer i = Integer.valueOf(item);
        		ints[index] = i;
        	} catch (NumberFormatException nfe) {
        		nfe.printStackTrace();
        		ints[index] = ArrayUtil.get(defaultvalue, index, null); //发生异常时，使用默认元素值
        	}
    		index ++;
		}
    	return ints;
    }
    public Integer[] getIntegers(String key){
    	return this.getIntegers(key, ",", null);
    }
    public int[] getInts(String key, String separator, int[] defaultvalue){
    	Integer[] ints = getIntegers(key,separator,null);
    	if(null==ints) return defaultvalue;
    	int[] is = new int[ints.length];
    	int index = 0;
    	for(Integer i: ints) {
    		is[index] = null==i ? ArrayUtil.get(defaultvalue, index, 0) : i;
    		index ++;
    	}
    	return is;
    }
    public int[] getInts(String key){
    	return getInts(key, ",", null);
    }
    
    public Long getLongInteger(String key, Long defaultvalue){
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	Long l = defaultvalue;
    	try {
    		l = Long.valueOf(value);
    	} catch (NumberFormatException nfe) {
    		nfe.printStackTrace();
    	}
    	return l;
    }
    public Long getLongInteger(String key){
    	return this.getLongInteger(key, null);
    }
    public long getLong(String key, long defaultvalue){
    	Long l = getLongInteger(key);
    	return null==l ? defaultvalue : l;
    }
    public long getLong(String key){
    	return getLong(key, (long)0);
    }
    
    public Long[] getLongIntegers(String key, String separator, Long[] defaultvalue){
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	String[] values = value.split(separator);
    	Long[] longs = new Long[]{};
    	int index = 0;
    	for(String item: values) {
    		try {
    			Long l = Long.valueOf(item);
        		longs[index] = l;
        	} catch (NumberFormatException nfe) {
        		nfe.printStackTrace();
        		longs[index] = ArrayUtil.get(defaultvalue, index, null); //发生异常时，使用默认元素值
        	}
    		index ++;
		}
    	return longs;
    }
    public Long[] getLongIntegers(String key){
    	return this.getLongIntegers(key, ",", null);
    }
    public long[] getLongs(String key, String separator, long[] defaultvalue){
    	Long[] longs = getLongIntegers(key,separator,null);
    	if(null==longs) return defaultvalue;
    	long[] ls = new long[longs.length];
    	int index = 0;
    	for(Long l: longs) {
    		ls[index] = null==l ? ArrayUtil.get(defaultvalue, index, 0) : l;
    		index ++;
    	}
    	return ls;
    }
    public long[] getLongs(String key){
    	return getLongs(key, ",", null);
    }
    
    public BigInteger getBigInteger(String key, BigInteger defaultvalue){
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	BigInteger bi = defaultvalue;
    	try {
    		bi = new BigInteger(value);
    	} catch (NumberFormatException nfe) {
    		nfe.printStackTrace();
    	}
    	return bi;
    }
    public BigInteger getBigInteger(String key){
    	return this.getBigInteger(key, null);
    }
    
    public BigInteger[] getBigIntegers(String key, String separator, BigInteger[] defaultvalue){
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	String[] values = value.split(separator);
    	BigInteger[] bigs = new BigInteger[]{};
    	int index = 0;
    	for(String item: values) {
    		try {
    			BigInteger bi = new BigInteger(item);
        		bigs[index] = bi;
        	} catch (NumberFormatException nfe) {
        		nfe.printStackTrace();
        		bigs[index] = ArrayUtil.get(defaultvalue, index, null); //发生异常时，使用默认元素值
        	}
    		index ++;
		}
    	return bigs;
    }
    public BigInteger[] getBigIntegers(String key){
    	return this.getBigIntegers(key, ",", null);
    }
    
    public Float getFloatDecimal(String key, Float defaultvalue){
    	String value = this.get(key);
    	if(null==value) return null;
    	Float f = null;
    	try {
    		f = Float.valueOf(value);
    	} catch (NumberFormatException nfe) {
    		nfe.printStackTrace();
    	}
    	return f;
    }
    public Float getFloatDecimal(String key){
    	return this.getFloatDecimal(key, null);
    }
    public float getFloat(String key, float defaultvalue){
    	Float f = getFloatDecimal(key);
    	return null==f ? defaultvalue : f;
    }
    public float getFloat(String key){
    	return getFloat(key, (float)0);
    }
    
    public Float[] getFloatDecimals(String key, String separator, Float[] defaultvalue){
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	String[] values = value.split(separator);
    	Float[] floats = new Float[]{};
    	int index = 0;
    	for(String item: values) {
    		try {
    			Float f = Float.valueOf(item);
        		floats[index] = f;
        	} catch (NumberFormatException nfe) {
        		nfe.printStackTrace();
        		floats[index] = ArrayUtil.get(defaultvalue, index, null); //发生异常时，使用默认元素值
        	}
    		index ++;
		}
    	return floats;
    }
    public Float[] getFloatDecimals(String key){
    	return this.getFloatDecimals(key, ",", null);
    }
    public float[] getFloats(String key, String separator, float[] defaultvalue){
    	Float[] floats = getFloatDecimals(key,separator,null);
    	if(null==floats) return defaultvalue;
    	float[] fs = new float[floats.length];
    	int index = 0;
    	for(Float f: floats) {
    		fs[index] = null==f ? ArrayUtil.get(defaultvalue, index, 0) : f;
    		index ++;
    	}
    	return fs;
    }
    public float[] getFloats(String key){
    	return getFloats(key, ",", null);
    }
    
    public Double getDoubleDecimal(String key, Double defaultvalue){
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	Double d = defaultvalue;
    	try {
    		d = Double.valueOf(value);
    	} catch (NumberFormatException nfe) {
    		nfe.printStackTrace();
    	}
    	return d;
    }
    public Double getDoubleDecimal(String key){
    	return this.getDoubleDecimal(key, null);
    }
    public double getDouble(String key, double defaultvalue){
    	Double d = getDoubleDecimal(key);
    	return null==d ? defaultvalue : d;
    }
    public double getDouble(String key){
    	return getDouble(key, (double)0);
    }
    
    public Double[] getDoubleDecimals(String key, String separator, Double[] defaultvalue){
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	String[] values = value.split(separator);
    	Double[] doubles = new Double[]{};
    	int index = 0;
    	for(String item: values) {
    		try {
    			Double d = Double.valueOf(item);
        		doubles[index] = d;
        	} catch (NumberFormatException nfe) {
        		nfe.printStackTrace();
        		doubles[index] = ArrayUtil.get(defaultvalue, index, null); //发生异常时，使用默认元素值
        	}
    		index ++;
		}
    	return doubles;
    }
    public Double[] getDoubleDecimals(String key){
    	return this.getDoubleDecimals(key, ",", null);
    }
    public double[] getDoubles(String key, String separator, double[] defaultvalue){
    	Double[] doubles = getDoubleDecimals(key,separator,null);
    	if(null==doubles) return defaultvalue;
    	double[] ds = new double[doubles.length];
    	int index = 0;
    	for(Double d: doubles) {
    		ds[index] = null==d ? ArrayUtil.get(defaultvalue, index, 0) : d;
    		index ++;
    	}
    	return ds;
    }
    public double[] getDoubles(String key){
    	return getDoubles(key, ",", null);
    }
    
    public String getString(String key, String defaultvalue){
    	String value = this.get(key);
    	return null==value ? defaultvalue : value;
    }
    public String getString(String key){
    	return this.getString(key, null);
    }
    
    public String[] getStrings(String key, String separator, String[] defaultvalue){
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	return value.split(separator);
    }
    public String[] getStrings(String key){
    	return this.getStrings(key, "\\r|\\n|\\r\\n", null);
    }
    
    
    
    // TODO ...
	public <E> E getEnum(Class<E> enumType, String key, E defaultvalue) {
		if(null==enumType) return defaultvalue;
    	String value = this.get(key);
    	if(StringUtil.Invalid(value)) return defaultvalue;
    	
    	E[] enums = enumType.getEnumConstants();
    	for(E e: enums) {
    		try {
    			Method method = e.getClass().getDeclaredMethod("name");
    			String name = (String) method.invoke(e, value);
    			if(name.equals(key)) return e;
    		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
    			// TODO Auto-generated catch block
    			ex.printStackTrace();
    			break;
    		}
    	}
    	
    	return defaultvalue;
    }

    public Object getJSON(String key, Type type, Object defaultvalue){
    	if(null==type) return defaultvalue;
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	return GsonUtil.JsonTo(value, type);
    }
    public Object getJSON(String key, Type type){
    	return this.getJSON(key, type, null);
    }

    public Object[] getJSONs(String key, String separator, Type type, Object[] defaultvalue){
    	if(null==type) return defaultvalue;
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	String[] values = value.split(separator);
    	Object[] objects = new Object[]{};
    	int index = 0;
    	for(String item: values) {
    		try {
    			Object o = GsonUtil.JsonTo(item, type);
    			objects[index++] = o;
        	} catch (RuntimeException re) {
        		re.printStackTrace();
    			objects[index++] = null;
        	}
		}
    	return objects;
    }
    public Object[] getJSONs(String key, String separator, Type type){
    	return this.getJSONs(key, separator, type, null);
    }

    public Object getXML(String key, Type type, Object defaultvalue) throws Exception {
    	throw new Exception("未实现！");
    }
    public Object getXML(String key, Type type) throws Exception {
    	return this.getXML(key, type, null);
    }
    
    public Object[] getXMLs(String key, String separator, Type type, Object[] defaultvalue) throws Exception {
    	throw new Exception("未实现！");
    }
    public Object[] getXMLs(String key, String separator, Type type) throws Exception {
    	return this.getXMLs(key, separator, type, null);
    }

    public Object getObject(String key, ISerializable serialization, Object defaultvalue){
    	if(null==serialization) return defaultvalue;
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	Object o = defaultvalue;
    	try {
    		o = serialization.deserialize(value);
    	} catch (RuntimeException re) {
    		re.printStackTrace();
    	}
    	return o;
    }
    public Object getObject(String key, ISerializable serialization){
    	return this.getObject(key, serialization, null);
    }

    public Object[] getObjects(String key, String separator, ISerializable serialization, Object[] defaultvalue){
    	if(null==serialization) return defaultvalue;
    	String value = this.get(key);
    	if(null==value) return defaultvalue;
    	String[] values = value.split(separator);
    	Object[] objects = new Object[]{};
    	int index = 0;
    	for(String item: values) {
    		try {
    			Object o = serialization.deserialize(item);
    			objects[index++] = o;
        	} catch (RuntimeException re) {
        		re.printStackTrace();
    			objects[index++] = null;
        	}
		}
    	return objects;
    }
    public Object[] getObjects(String key, String separator, ISerializable serialization){
    	return this.getObjects(key, separator, serialization, null);
    }
}
