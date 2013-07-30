package com.troy.jd.decompiler;

import com.troy.jd.attr.Attrs;

/**
 * field_info {
 * u2 access_flags;
 * u2 name_index;
 * u2 descriptor_index;
 * u2 attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 * 
 * @author WuCh
 * 
 */
public class FieldInfo implements Fillable {
	private AccessFlags accessFlags;
	private int nameIndex;
	private int descriptorIndex;
	private Attrs attrs;
	private TClass tclass;

	public FieldInfo(TClass tclass) {
		this.tclass = tclass;
	}

	public void fill(Decomplier de) throws Exception {
		accessFlags = new AccessFlags(de.readU2());
		nameIndex = de.readU2();
		descriptorIndex = de.readU2();

		attrs = new Attrs(tclass);
		attrs.fill(de);
	}

	public AccessFlags getAccessFlags() {
		return accessFlags;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}

	public String getName() {
		return tclass.getFullDescriptionOf(nameIndex);
	}

	public String getDescriptor() {
		return tclass.getFullDescriptionOf(descriptorIndex);
	}

	public Attrs getAttrs() {
		return attrs;
	}
}
