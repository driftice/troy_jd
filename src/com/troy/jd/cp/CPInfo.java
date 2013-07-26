package com.troy.jd.cp;

import com.troy.jd.de.Fillable;
import com.troy.jd.de.TClass;

//constant pool info
public abstract class CPInfo implements Fillable {
	protected int tag;
	protected TClass tclass;

	public CPInfo(int tag, TClass tclass) {
		this.tclass = tclass;
		this.tag = tag;
	}

	public int getTag() {
		return tag;
	}

	public TClass getTClass() {
		return tclass;
	}

	public boolean isNull() {
		return false;
	}

	public abstract String getDescription();

	public abstract String getFullDescription();
}
