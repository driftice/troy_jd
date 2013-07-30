package com.troy.jd.pool;

import com.troy.jd.decompiler.Decomplier;
import com.troy.jd.decompiler.TClass;

public class ClassCPInfo extends CPInfo {
	private int nameIndex;

	public ClassCPInfo(int tag, TClass tclass) {
		super(tag, tclass);
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		nameIndex = de.readU2();
	}

	@Override
	public String getDescription() {
		return "#" + nameIndex;
	}

	@Override
	public String getFullDescription() {
		return tclass.getFullDescriptionOf(nameIndex);
	}

}
