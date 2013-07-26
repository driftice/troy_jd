package com.troy.jd.cp;

import com.troy.jd.de.Decomplier;
import com.troy.jd.de.TClass;

public class StringCPInfo extends CPInfo {
	private int stringIndex;

	public StringCPInfo(int tag, TClass tclass) {
		super(tag, tclass);
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		stringIndex = de.readU2();
	}

	@Override
	public String getDescription() {
		return String.valueOf(stringIndex);
	}

	@Override
	public String getFullDescription() {
		return getDescription();
	}

	public int getStringIndex() {
		return stringIndex;
	}
}
