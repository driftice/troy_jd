package com.troy.jd.pool;

import com.troy.jd.decompiler.Decomplier;
import com.troy.jd.decompiler.TClass;

public class RefCPInfo extends CPInfo {
	private int classIndex;
	private int nameIndex;

	public RefCPInfo(int tag, TClass tclass) {
		super(tag, tclass);
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		classIndex = de.readU2();
		nameIndex = de.readU2();
	}

	@Override
	public String getDescription() {
		return "#" + classIndex + ".#" + nameIndex;
	}

	@Override
	public String getFullDescription() {
		return tclass.getFullDescriptionOf(classIndex) + "." + tclass.getFullDescriptionOf(nameIndex);
	}

	public int getClassIndex() {
		return classIndex;
	}

	public int getNameIndex() {
		return nameIndex;
	}
}
