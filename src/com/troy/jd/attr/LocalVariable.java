package com.troy.jd.attr;

import com.troy.jd.de.Decomplier;
import com.troy.jd.de.Fillable;
import com.troy.jd.de.TClass;

/**
 * { u2 start_pc;
 * u2 length;
 * u2 name_index;
 * u2 descriptor_index;
 * u2 index; //slot
 * }
 * 
 * @author WuCh
 * 
 */
public class LocalVariable implements Fillable {
	private TClass tclass;
	private int startPC;
	private int length;
	private int nameIndex;
	private int descriptorIndex;
	private int slot;

	public LocalVariable(TClass tclass) {
		this.tclass = tclass;
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		startPC = de.readU2();
		length = de.readU2();
		nameIndex = de.readU2();
		descriptorIndex = de.readU2();
		slot = de.readU2();
	}

	public String getName() {
		return tclass.getFullDescriptionOf(nameIndex);
	}

	public String getDescriptor() {
		return tclass.getFullDescriptionOf(descriptorIndex);
	}

	@Override
	public String toString() {
		return startPC + "," + length + "," + slot + "," + getName() + ","
				+ getDescriptor();
	}

	public int getStartPC() {
		return startPC;
	}

	public int getLength() {
		return length;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}

	public int getSlot() {
		return slot;
	}
}
