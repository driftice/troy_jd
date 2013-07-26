package com.troy.jd.cp;

import com.troy.jd.de.Constants;
import com.troy.jd.de.TClass;

/**
 * CONSTANT_Class 7
 * CONSTANT_Fieldref 9
 * CONSTANT_Methodref 10
 * CONSTANT_InterfaceMethodref 11
 * CONSTANT_String 8
 * CONSTANT_Integer 3
 * CONSTANT_Float 4
 * CONSTANT_Long 5
 * CONSTANT_Double 6
 * CONSTANT_NameAndType 12
 * CONSTANT_Utf8 1
 * 
 * @author WuCh
 * 
 */
public class CPInfoTable {
	public static CPInfo findByTag(int tag, TClass tclass) {
		try {
			if (tag == Constants.CONSTANT_Utf8) {
				return new UTF8CPInfo(tag, tclass);
			} else if (tag == Constants.CONSTANT_Integer) {
				return new IntegerCPInfo(tag, tclass);
			} else if (tag == Constants.CONSTANT_Float) {
				return new FloatCPInfo(tag, tclass);
			} else if (tag == Constants.CONSTANT_Long) {
				return new LongCPInfo(tag, tclass);
			} else if (tag == Constants.CONSTANT_Double) {
				return new DoubleCPInfo(tag, tclass);
			} else if (tag == Constants.CONSTANT_Class) {
				return new ClassCPInfo(tag, tclass);
			} else if (tag == Constants.CONSTANT_String) {
				return new StringCPInfo(tag, tclass);
			} else if (tag == Constants.CONSTANT_Fieldref) {
				return new FieldRefCPInfo(tag, tclass);
			} else if (tag == Constants.CONSTANT_Methodref) {
				return new MethodRefCPInfo(tag, tclass);
			} else if (tag == Constants.CONSTANT_InterfaceMethodref) {
				return new InterfaceMethodRefCPInfo(tag, tclass);
			} else if (tag == Constants.CONSTANT_NameAndType) {
				return new NameAndTypeCPInfo(tag, tclass);
			} else {
				return new NullCPInfo(tag, tclass);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
