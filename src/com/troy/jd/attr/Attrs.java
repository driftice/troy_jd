package com.troy.jd.attr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.troy.jd.decompiler.Decomplier;
import com.troy.jd.decompiler.Fillable;
import com.troy.jd.decompiler.TClass;

public class Attrs implements Fillable {
	private List<AttrInfo> attrInfos = new ArrayList<>();
	private TClass tclass;

	public Attrs(TClass tclass) {
		super();
		this.tclass = tclass;
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		int attrLen = de.readU2();
		for (int i = 0; i < attrLen; i++) {
			int attrNameIndex = de.readU2();
			AttrInfo attrInfo = AttrInfoTable.findAttrByNameIndex(
					attrNameIndex, tclass);
			attrInfo.fill(de);
			attrInfos.add(attrInfo);
		}
	}

	public List<AttrInfo> getAttrInfos() {
		return Collections.unmodifiableList(attrInfos);
	}

	public int size(){
		return attrInfos.size();
	}
}
