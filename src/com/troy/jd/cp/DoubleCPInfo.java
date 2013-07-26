package com.troy.jd.cp;

import com.troy.jd.de.Decomplier;
import com.troy.jd.de.TClass;

public class DoubleCPInfo extends CPInfo {
	private double value;

	public DoubleCPInfo(int tag, TClass tclass) {
		super(tag, tclass);
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		value = de.readDouble();
	}

	@Override
	public String getDescription() {
		return String.valueOf(value);
	}

	@Override
	public String getFullDescription() {
		return getDescription();
	}

	public double getValue() {
		return value;
	}
}
