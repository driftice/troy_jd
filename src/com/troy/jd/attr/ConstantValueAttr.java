package com.troy.jd.attr;

import com.troy.jd.decompiler.Decomplier;
import com.troy.jd.decompiler.TClass;
import com.troy.jd.pool.CPInfo;

/**
 *  ConstantValue_attribute {
    	u2 attribute_name_index;
    	u4 attribute_length;
    	u2 constantvalue_index;
    }
 * @author WuCh
 *
 */
public class ConstantValueAttr extends AttrInfo {
	private int constantIndex;

	public ConstantValueAttr(int attrNameIndex, TClass tclass) {
		super(attrNameIndex, tclass);
	}

	public void fill(Decomplier de) throws Exception {
		len = de.readU4();
		constantIndex = de.readU2();
	}

	public int getConstantIndex() {
		return constantIndex;
	}

	public CPInfo getConstant() {
		return tclass.getCPInfoOf(constantIndex);
	}

	@Override
	public String toString() {
		return getConstant().getFullDescription();
	}
}
