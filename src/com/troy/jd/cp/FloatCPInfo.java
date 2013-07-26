package com.troy.jd.cp;

import com.troy.jd.de.Decomplier;
import com.troy.jd.de.TClass;

public class FloatCPInfo extends CPInfo {
	private float value;

	public FloatCPInfo(int tag,TClass tclass) {
		super(tag,tclass);
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		value = de.readFloat();
	}

	@Override
	public String getDescription() {
		return String.valueOf(value);
	}

	@Override
	public String getFullDescription() {
		return getDescription();
	}

	public float getValue() {
		return value;
	}
}
