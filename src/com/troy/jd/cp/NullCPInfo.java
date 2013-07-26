package com.troy.jd.cp;

import com.troy.jd.de.Decomplier;
import com.troy.jd.de.TClass;

/**
 * 规范中不存在的常量，只是用来占第0个索引位
 * 
 * @author WuCh
 * 
 */
public class NullCPInfo extends CPInfo {
	public NullCPInfo(int tag, TClass tclass) {
		super(tag, tclass);
	}

	@Override
	public void fill(Decomplier de) throws Exception {
	}

	@Override
	public String getDescription() {
		return "NULL";
	}

	@Override
	public String getFullDescription() {
		return "NULL";
	}

	@Override
	public boolean isNull() {
		return true;
	}

}
