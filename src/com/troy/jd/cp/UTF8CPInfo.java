package com.troy.jd.cp;

import com.troy.jd.de.Decomplier;
import com.troy.jd.de.TClass;

public class UTF8CPInfo extends CPInfo {
	private String value;

	public UTF8CPInfo(int tag,TClass tclass) {
		super(tag,tclass);
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		int len = de.readU2();
		byte[] buf = de.readArray(len);
		this.value = new String(buf, "utf-8");
	}

	@Override
	public String getDescription() {
		return value;
	}

	@Override
	public String getFullDescription() {
		return getDescription();
	}

	public String getValue() {
		return value;
	}

}
