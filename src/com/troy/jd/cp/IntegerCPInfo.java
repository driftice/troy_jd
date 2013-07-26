package com.troy.jd.cp;

import com.troy.jd.de.Decomplier;
import com.troy.jd.de.TClass;

public class IntegerCPInfo extends CPInfo {
	private int value;

	public IntegerCPInfo(int tag, TClass tclass) {
		super(tag, tclass);
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		value = de.readInt();
	}

	@Override
	public String getDescription() {
		return String.valueOf(value);
	}

	@Override
	public String getFullDescription() {
		return getDescription();
	}

	public int getValue() {
		return value;
	}

}
