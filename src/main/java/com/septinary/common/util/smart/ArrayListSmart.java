package com.septinary.common.util.smart;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @Filename: com.septinary.common.util.smart.ArrayListSmart.java of the project [com.septinary.common]
 *     @Type: ArrayListSmart
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年6月6日 下午12:35:20
 *  @param <T>
 */
public class ArrayListSmart<T> extends ArrayList<T> {
	private static final long serialVersionUID = -3942630799332427175L;

	public ArrayListSmart() {
        super();
    }
	
	public ArrayListSmart(int initialCapacity) {
        super(initialCapacity);
    }

    public ArrayListSmart(Collection<? extends T> c) {
        super(c);
    }
    
    /**
     * 线性add
     * @param e
     * @return
     */
    public ArrayListSmart<T> append(T e) {
    	super.add(e);
    	return this;
    }
    
    /**
     * 线性add
     * @param index
     * @param e
     * @return
     */
    public ArrayListSmart<T> append(int index, T e) {
    	super.add(index, e);
    	return this;
    }
    
    /**
     * 线性addAll
     * @param c
     * @return
     */
    public ArrayListSmart<T> appendAll(Collection<? extends T> c) {
    	super.addAll(c);
    	return this;
    }
    
    /**
     * 线性addAll
     * @param c
     * @return
     */
    public ArrayListSmart<T> appendAll(int index, Collection<? extends T> c) {
    	super.addAll(index, c);
    	return this;
    }
    
    /**
     * 线性remove
     * @param index
     * @return
     */
    public ArrayListSmart<T> rmv(int index) {
    	super.remove(index);
    	return this;
    }

    /**
     * 线性remove
     * @param o
     * @return
     */
    public ArrayListSmart<T> rmv(Object o) {
    	super.remove(o);
    	return this;
    }

    /**
     * 线性removeAll
     * @param c
     * @return
     */
    public ArrayListSmart<T> rmvAll(Collection<?> c) {
    	super.removeAll(c);
    	return this;
    }

    /**
     * 线性removeRange
     * @param fromIndex
     * @param toIndex
     * @return
     */
    protected ArrayListSmart<T> rmvRange(int fromIndex, int toIndex) {
    	super.removeRange(fromIndex, toIndex);
    	return this;
    }
    
    /**
     * 线性retainAll
     * @param c
     * @return
     */
    public ArrayListSmart<T> rmvOtherAll(Collection<?> c) {
    	super.retainAll(c);
    	return this;
    }
    
    /**
     * 线性set
     * @param index
     * @param e
     * @return
     */
    public ArrayListSmart<T> setElement(int index, T e) {
    	super.set(index, e);
    	return this;
    }
    
    /**
     * 线性trimToSize
     * @return
     */
    public ArrayListSmart<T> trimSize() {
    	super.trimToSize();
    	return this;
    }
    
    /**
     * 线性clear
     * @return
     */
    public ArrayListSmart<T> clr() {
    	super.clear();
    	return this;
    }

}
