package com.troy.jd.attr;

import com.troy.jd.de.Decomplier;
import com.troy.jd.de.Fillable;
import com.troy.jd.de.TClass;

/**
 * { u2 start_pc;
 * u2 end_pc;
 * u2 handler_pc;
 * u2 catch_type;
 * }
 * 
 * @author WuCh
 * 
 */
public class ExceptionInfo implements Fillable {
	private TClass tclass;
	private int startPC;
	private int endPC;
	private int handlerPC;
	private int catchTypeIndex;

	public ExceptionInfo(TClass tclass) {
		this.tclass = tclass;
	}

	public void fill(Decomplier de) throws Exception {
		startPC = de.readU2();
		endPC = de.readU2();
		handlerPC = de.readU2();
		catchTypeIndex = de.readU2();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(startPC + "," + endPC + "," + handlerPC + ","
				+ getCatchType());
		return sb.toString();
	}

	public TClass getTclass() {
		return tclass;
	}

	public int getStartPC() {
		return startPC;
	}

	public int getEndPC() {
		return endPC;
	}

	public int getHandlerPC() {
		return handlerPC;
	}

	public int getCatchTypeIndex() {
		return catchTypeIndex;
	}

	public String getCatchType() {
		return tclass.getFullDescriptionOf(catchTypeIndex);
	}

}
