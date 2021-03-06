package com.troy.jd.pool;

import com.troy.jd.decompiler.Decomplier;
import com.troy.jd.decompiler.TClass;

public class NameAndTypeCPInfo extends CPInfo {
	private int nameIndex;
	private int descriptorIndex;

	public NameAndTypeCPInfo(int tag, TClass tclass) {
		super(tag, tclass);
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		nameIndex = de.readU2();
		descriptorIndex = de.readU2();
	}

	@Override
	public String getDescription() {
		return "#" + nameIndex + ".#" + descriptorIndex;
	}

	@Override
	public String getFullDescription() {
		return tclass.getFullDescriptionOf(nameIndex) + "."
				+ tclass.getFullDescriptionOf(descriptorIndex);
	}

	public int getClassIndex() {
		return nameIndex;
	}

	public int getNameIndex() {
		return descriptorIndex;
	}

}
