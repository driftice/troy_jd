package com.troy.jd.attr;

import com.troy.jd.decompiler.TClass;

public class AttrInfoTable {
	public static AttrInfo findAttrByNameIndex(int attrNameIndex, TClass tclass) {
		String name = tclass.getFullDescriptionOf(attrNameIndex);
		if ("Code".equalsIgnoreCase(name)) {
			return new CodeAttr(attrNameIndex, tclass);
		} else if ("ConstantValue".equalsIgnoreCase(name)) {
			return new ConstantValueAttr(attrNameIndex, tclass);
		} else if ("SourceFile".equalsIgnoreCase(name)) {
			return new SourceFileAttr(attrNameIndex, tclass);
		} else if ("LineNumberTable".equalsIgnoreCase(name)) {
			return new LineNumberTableAttr(attrNameIndex, tclass);
		} else if ("LocalVariableTable".equalsIgnoreCase(name)) {
			return new LocalVariableTableAttr(attrNameIndex, tclass);
		} else {
			return new UnknownAttrInfo(attrNameIndex, tclass);
		}
	}
}
