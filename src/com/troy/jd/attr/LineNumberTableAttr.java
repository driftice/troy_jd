package com.troy.jd.attr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.troy.jd.de.Decomplier;
import com.troy.jd.de.TClass;

/**
 * LineNumberTable_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 line_number_table_length;
 * { u2 start_pc;
 * u2 line_number;
 * } line_number_table[line_number_table_length];
 * }
 * 
 * @author WuCh
 * 
 */
public class LineNumberTableAttr extends AttrInfo {
	private List<LineNumber> linesNumbers = new ArrayList<>();

	public LineNumberTableAttr(int attrNameIndex, TClass tclass) {
		super(attrNameIndex, tclass);
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		len = de.readU4();

		int lineNumberTableLen = de.readU2();
		for (int i = 0; i < lineNumberTableLen; i++) {
			LineNumber number = new LineNumber(tclass);
			number.fill(de);
			linesNumbers.add(number);
		}
	}

	public List<LineNumber> getLinesNumbers() {
		return Collections.unmodifiableList(linesNumbers);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (LineNumber line : linesNumbers) {
			sb.append(line.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
