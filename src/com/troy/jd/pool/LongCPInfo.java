package com.troy.jd.pool;

import com.troy.jd.decompiler.Decomplier;
import com.troy.jd.decompiler.TClass;

public class LongCPInfo extends CPInfo {
	private long value;

	public LongCPInfo(int tag,TClass tclass) {
		super(tag,tclass);
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		value = de.readLong();
	}

	@Override
	public String getDescription() {
		return String.valueOf(value);
	}

	@Override
	public String getFullDescription() {
		return getDescription();
	}

	public long getValue() {
		return value;
	}

}
