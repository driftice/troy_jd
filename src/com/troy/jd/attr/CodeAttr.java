package com.troy.jd.attr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.troy.jd.decompiler.Decomplier;
import com.troy.jd.decompiler.TClass;
import com.troy.jd.opcode.Opcodes;

/**
 * 
 Code_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 max_stack;
 * u2 max_locals;
 * u4 code_length;
 * u1 code[code_length];
 * u2 exception_table_length;
 * exception_table[exception_table_length];
 * u2 attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 * 
 * @author WuCh
 * 
 */
public class CodeAttr extends AttrInfo {
	private int maxStack;
	private int maxLocals;
	private Opcodes opbytes;
	private List<ExceptionInfo> exceptions = new ArrayList<>();
	private Attrs attrs;

	public CodeAttr(int attrNameIndex, TClass tclass) {
		super(attrNameIndex, tclass);
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		len = de.readU4();

		maxStack = de.readU2();
		maxLocals = de.readU2();

		opbytes = new Opcodes(tclass);
		opbytes.fill(de);

		int exceptionCount = de.readU2();
		for (int i = 0; i < exceptionCount; i++) {
			ExceptionInfo exInfo = new ExceptionInfo(tclass);
			exInfo.fill(de);
			exceptions.add(exInfo);
		}

		attrs = new Attrs(tclass);
		attrs.fill(de);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("stack:" + maxStack + ",locals:" + maxLocals);
		sb.append("\n");
		sb.append("\n");
		sb.append("Code (" + opbytes.getCodeLength() + "bytes):");
		sb.append("\n");
		sb.append(opbytes.toString());
		sb.append("\n");
		sb.append("Exception table (" + exceptions.size() + "):");
		sb.append("\n");
		sb.append("start_pc , end_pc , handler_pc ,catch_type");
		sb.append("\n");
		for (ExceptionInfo exInfo : exceptions) {
			sb.append(exInfo.toString());
			sb.append("\n");
		}
		sb.append("\n");

		for (AttrInfo attr : attrs.getAttrInfos()) {
			sb.append(attr.getName());
			sb.append("\n");
			sb.append(attr.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	public int getMaxStack() {
		return maxStack;
	}

	public int getMaxLocals() {
		return maxLocals;
	}

	public List<ExceptionInfo> getExceptions() {
		return Collections.unmodifiableList(exceptions);
	}

	public Attrs getAttrs() {
		return attrs;
	}

	public Opcodes getOpbytes() {
		return opbytes;
	}
}
