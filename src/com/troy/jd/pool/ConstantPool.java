package com.troy.jd.pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.troy.jd.decompiler.Decomplier;
import com.troy.jd.decompiler.Fillable;
import com.troy.jd.decompiler.TClass;

public class ConstantPool implements Fillable {
	private List<CPInfo> constants = new ArrayList<>();
	private TClass tclass;
	private NullCPInfo NULL;

	public ConstantPool(TClass tclass) {
		this.tclass = tclass;
		NULL = new NullCPInfo(0, tclass);
		constants.add(NULL); // index 0 item is null
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		int poolCount = de.readU2() - 1;
		for (int i = 0; i < poolCount; i++) {
			int tag = de.readU1();
			CPInfo cpInfo = CPInfoTable.findByTag(tag, tclass);
			cpInfo.fill(de);
			constants.add(cpInfo);

			// double and long占两个常量池位
			if (cpInfo instanceof DoubleCPInfo || cpInfo instanceof LongCPInfo) {
				constants.add(NULL);
				i++;
			}
		}
	}

	public CPInfo getCPInfoOf(int index) {
		return constants.get(index);
	}

	public String getFullDescriptionOf(int index) {
		return getCPInfoOf(index).getFullDescription();
	}

	public List<CPInfo> getConstants() {
		return Collections.unmodifiableList(constants);
	}

	public int size() {
		return constants.size();
	}
}
