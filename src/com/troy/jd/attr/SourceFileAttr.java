package com.troy.jd.attr;

import com.troy.jd.de.Decomplier;
import com.troy.jd.de.TClass;

/**
 * SourceFile_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 sourcefile_index;
 * }
 * 
 * @author WuCh
 * 
 */
public class SourceFileAttr extends AttrInfo {
	private int sourceFileIndex;

	public SourceFileAttr(int attrNameIndex, TClass tclass) {
		super(attrNameIndex, tclass);
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		len = de.readU4();
		
		sourceFileIndex = de.readU2();
	}

	public int getSourceFileIndex() {
		return sourceFileIndex;
	}

	public String getSourceFile() {
		return tclass.getFullDescriptionOf(sourceFileIndex);
	}

	@Override
	public String toString() {
		return getSourceFile();
	}
}
