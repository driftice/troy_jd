package com.troy.jd.attr;

import com.troy.jd.de.Decomplier;
import com.troy.jd.de.Fillable;
import com.troy.jd.de.TClass;

/**
 * * { u2 start_pc;
 * u2 line_number;
 * }
 * 
 * @author WuCh
 * 
 */
public class LineNumber implements Fillable {
	private int startPC;
	private int lineNumber;
	private TClass tclass;

	public LineNumber(TClass tclass) {
		this.tclass = tclass;
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		startPC = de.readU2();
		lineNumber = de.readU2();
	}

	public int getStartPC() {
		return startPC;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	@Override
	public String toString() {
		return "line " + lineNumber + ":" + startPC;
	}

}
