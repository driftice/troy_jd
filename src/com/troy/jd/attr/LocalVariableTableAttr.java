package com.troy.jd.attr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.troy.jd.de.Decomplier;
import com.troy.jd.de.TClass;

/**
 * 
 LocalVariableTable_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 local_variable_table_length;
 * { u2 start_pc;
 * u2 length;
 * u2 name_index;
 * u2 descriptor_index;
 * u2 index;
 * } local_variable_table[local_variable_table_length];
 * }
 * 
 * @author WuCh
 * 
 */
public class LocalVariableTableAttr extends AttrInfo {
	private List<LocalVariable> localVariables = new ArrayList<>();

	public LocalVariableTableAttr(int attrNameIndex, TClass tclass) {
		super(attrNameIndex, tclass);
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		len = de.readU4();

		int localVariableTableLen = de.readU2();
		for (int i = 0; i < localVariableTableLen; i++) {
			LocalVariable var = new LocalVariable(tclass);
			var.fill(de);
			localVariables.add(var);
		}
	}

	public List<LocalVariable> getLocalVariables() {
		return Collections.unmodifiableList(localVariables);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (LocalVariable var : localVariables) {
			sb.append(var.toString());
			sb.append("\n");
		}
		return sb.toString();

	}
}
