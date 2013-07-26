package com.troy.jd.attr;

import com.troy.jd.de.Decomplier;
import com.troy.jd.de.Fillable;
import com.troy.jd.de.TClass;

public abstract class AttrInfo implements Fillable {
	protected int attrNameIndex;
	protected int len;
	protected TClass tclass;
	protected byte[] bytes;

	public AttrInfo(int attrNameIndex, TClass tclass) {
		this.attrNameIndex = attrNameIndex;
		this.tclass = tclass;
	}

	public int getAttrNameIndex() {
		return attrNameIndex;
	}

	public int getLen() {
		return len;
	}

	public String getName() {
		return tclass.getFullDescriptionOf(attrNameIndex);
	}

	// 默认实现，具体属性需要被覆盖
	public void fill(Decomplier de) throws Exception {
		len = de.readU4();
		bytes = de.readArray(len);
	}

	public String toString() {
		return getName();
	}
}
